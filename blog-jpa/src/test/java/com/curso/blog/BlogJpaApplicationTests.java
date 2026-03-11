package com.curso.blog;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.exception.BlogExceptions.*;
import com.curso.blog.service.EtiquetaService;
import com.curso.blog.service.PostService;
import com.curso.blog.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests de integración — usan H2 en memoria (perfil 'test').
 *
 * @SpringBootTest levanta el contexto completo de Spring.
 * @Transactional hace rollback después de cada test → BD limpia.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BlogJpaApplicationTests {

    @Autowired UsuarioService  usuarioService;
    @Autowired PostService     postService;
    @Autowired EtiquetaService etiquetaService;

    // ================================================================
    // Tests: @OneToOne — Usuario y Perfil
    // ================================================================

    @Test
    @Order(1)
    @DisplayName("✅ Crear usuario con perfil (@OneToOne)")
    void crearUsuarioConPerfil() {
        // Arrange
        var request = CrearUsuarioRequest.builder()
            .nombre("Test User")
            .email("test@blog.com")
            .password("123456")
            .perfil(CrearPerfilRequest.builder()
                .bio("Bio de prueba")
                .ubicacion("Santiago")
                .build())
            .build();

        // Act
        UsuarioResponse response = usuarioService.crearUsuario(request);

        // Assert
        assertThat(response.getId()).isNotNull();
        assertThat(response.getNombre()).isEqualTo("Test User");
        assertThat(response.getPerfil()).isNotNull();
        assertThat(response.getPerfil().getBio()).isEqualTo("Bio de prueba");
        assertThat(response.getPerfil().getUbicacion()).isEqualTo("Santiago");
    }

    @Test
    @Order(2)
    @DisplayName("✅ No permite emails duplicados")
    void noPermiteEmailDuplicado() {
        // Arrange — crear primer usuario
        var request = CrearUsuarioRequest.builder()
            .nombre("Usuario A").email("duplicado@blog.com").password("123456").build();
        usuarioService.crearUsuario(request);

        // Act & Assert — segundo usuario con mismo email debe fallar
        var request2 = CrearUsuarioRequest.builder()
            .nombre("Usuario B").email("duplicado@blog.com").password("654321").build();
        assertThatThrownBy(() -> usuarioService.crearUsuario(request2))
            .isInstanceOf(RecursoDuplicadoException.class)
            .hasMessageContaining("duplicado@blog.com");
    }

    @Test
    @Order(3)
    @DisplayName("✅ Actualizar perfil de usuario")
    void actualizarPerfil() {
        // Arrange
        var usuario = usuarioService.crearUsuario(
            CrearUsuarioRequest.builder()
                .nombre("Ana").email("ana@test.com").password("123456").build());

        // Act
        PerfilResponse perfil = usuarioService.actualizarPerfil(usuario.getId(),
            CrearPerfilRequest.builder().bio("Nueva bio").sitioWeb("https://ana.dev").build());

        // Assert
        assertThat(perfil.getBio()).isEqualTo("Nueva bio");
        assertThat(perfil.getSitioWeb()).isEqualTo("https://ana.dev");
    }

    // ================================================================
    // Tests: @OneToMany — Usuario y Posts
    // ================================================================

    @Test
    @Order(4)
    @DisplayName("✅ Crear post asociado a un autor (@OneToMany)")
    void crearPostConAutor() {
        // Arrange
        var usuario = usuarioService.crearUsuario(
            CrearUsuarioRequest.builder()
                .nombre("Luis").email("luis@test.com").password("123456").build());

        var postRequest = CrearPostRequest.builder()
            .titulo("Mi primer post")
            .contenido("Contenido del post de prueba en JPA")
            .resumen("Resumen del post")
            .autorId(usuario.getId())
            .build();

        // Act
        PostResponse post = postService.crearPost(postRequest);

        // Assert
        assertThat(post.getId()).isNotNull();
        assertThat(post.getAutor().getId()).isEqualTo(usuario.getId());
        assertThat(post.getAutor().getNombre()).isEqualTo("Luis");
    }

    @Test
    @Order(5)
    @DisplayName("✅ Publicar un post cambia su estado y fecha")
    void publicarPost() {
        // Arrange
        var usuario = usuarioService.crearUsuario(
            CrearUsuarioRequest.builder()
                .nombre("Maria").email("maria@test.com").password("123456").build());
        var post = postService.crearPost(
            CrearPostRequest.builder()
                .titulo("Post a publicar").contenido("Contenido").autorId(usuario.getId()).build());

        // Act
        PostResponse publicado = postService.publicarPost(post.getId());

        // Assert
        assertThat(publicado.getEstado().name()).isEqualTo("PUBLICADO");
        assertThat(publicado.getFechaPublicacion()).isNotNull();
    }

    // ================================================================
    // Tests: @ManyToMany — Posts y Etiquetas
    // ================================================================

    @Test
    @Order(6)
    @DisplayName("✅ Asociar etiquetas a un post (@ManyToMany)")
    void asociarEtiquetasAPost() {
        // Arrange
        var usuario = usuarioService.crearUsuario(
            CrearUsuarioRequest.builder()
                .nombre("Pedro").email("pedro@test.com").password("123456").build());

        var etiqueta1 = etiquetaService.crearEtiqueta(
            CrearEtiquetaRequest.builder().nombre("java").color("#F89820").build());
        var etiqueta2 = etiquetaService.crearEtiqueta(
            CrearEtiquetaRequest.builder().nombre("spring").color("#6DB33F").build());

        var post = postService.crearPost(
            CrearPostRequest.builder()
                .titulo("Post con etiquetas")
                .contenido("Contenido")
                .autorId(usuario.getId())
                .etiquetaIds(Set.of(etiqueta1.getId(), etiqueta2.getId()))
                .build());

        // Assert
        assertThat(post.getEtiquetas()).hasSize(2);
        assertThat(post.getEtiquetas())
            .extracting(EtiquetaResponse::getNombre)
            .containsExactlyInAnyOrder("java", "spring");
    }

    @Test
    @Order(7)
    @DisplayName("✅ Remover etiqueta de un post")
    void removerEtiquetaDePost() {
        // Arrange
        var usuario = usuarioService.crearUsuario(
            CrearUsuarioRequest.builder()
                .nombre("Sofia").email("sofia@test.com").password("123456").build());
        var etiqueta = etiquetaService.crearEtiqueta(
            CrearEtiquetaRequest.builder().nombre("jpa").color("#59B4C3").build());
        var post = postService.crearPost(
            CrearPostRequest.builder()
                .titulo("Post").contenido("Contenido").autorId(usuario.getId())
                .etiquetaIds(Set.of(etiqueta.getId())).build());

        // Act
        PostResponse actualizado = postService.removerEtiqueta(post.getId(), etiqueta.getId());

        // Assert
        assertThat(actualizado.getEtiquetas()).isEmpty();
    }

    @Test
    @Order(8)
    @DisplayName("✅ No permite eliminar etiqueta en uso")
    void noEliminaEtiquetaEnUso() {
        // Arrange
        var usuario = usuarioService.crearUsuario(
            CrearUsuarioRequest.builder()
                .nombre("Carlos").email("carlos@test.com").password("123456").build());
        var etiqueta = etiquetaService.crearEtiqueta(
            CrearEtiquetaRequest.builder().nombre("mysql").color("#4479A1").build());
        postService.crearPost(
            CrearPostRequest.builder()
                .titulo("Post MySQL").contenido("Contenido").autorId(usuario.getId())
                .etiquetaIds(Set.of(etiqueta.getId())).build());

        // Act & Assert
        assertThatThrownBy(() -> etiquetaService.eliminarEtiqueta(etiqueta.getId()))
            .isInstanceOf(OperacionInvalidaException.class)
            .hasMessageContaining("en uso");
    }

    @Test
    @Order(9)
    @DisplayName("✅ Lanzar excepción al buscar recurso inexistente")
    void recursosNoExistentes() {
        assertThatThrownBy(() -> usuarioService.obtenerPorId(9999L))
            .isInstanceOf(RecursoNoEncontradoException.class);
        assertThatThrownBy(() -> postService.obtenerPorId(9999L))
            .isInstanceOf(RecursoNoEncontradoException.class);
    }
}
