package com.curso.blog.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.curso.blog.entity.Etiqueta;
import com.curso.blog.entity.Perfil;
import com.curso.blog.entity.Post;
import com.curso.blog.entity.Usuario;
import com.curso.blog.repository.EtiquetaRepository;
import com.curso.blog.repository.PostRepository;
import com.curso.blog.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Inicializador de datos de prueba.
 * Se ejecuta al iniciar la aplicación SOLO en el perfil 'dev'.
 *
 * Actívalo con: spring.profiles.active=dev en application.properties
 */
@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository  usuarioRepo;
    private final EtiquetaRepository etiquetaRepo;
    private final PostRepository     postRepo;

    @Override
    @Transactional
    public void run(String... args) {

        if (usuarioRepo.count() > 0) {
            log.info("Base de datos ya tiene datos — omitiendo inicialización");
            return;
        }

        log.info("=== Inicializando datos de prueba ===");

        // 1. Crear etiquetas
        Etiqueta java   = crearEtiqueta("java",       "#F89820");
        Etiqueta spring = crearEtiqueta("spring-boot","#6DB33F");
        Etiqueta jpa    = crearEtiqueta("jpa",        "#59B4C3");
        Etiqueta mysql  = crearEtiqueta("mysql",       "#4479A1");

        // 2. Crear usuarios con perfiles (relación @OneToOne)
        Usuario ana = crearUsuario("Ana García",  "ana@blog.com",  "123456",
            "Desarrolladora Java con 5 años de experiencia en Spring Boot",
            "https://foto.ana.com", "https://anagarcia.dev", "Santiago");

        Usuario luis = crearUsuario("Luis Pérez", "luis@blog.com", "123456",
            "Arquitecto de software, apasionado por DDD y microservicios",
            null, null, "Valparaíso");

        // 3. Crear posts con relación @ManyToOne → @OneToMany
        Post post1 = crearPost(
            "Introducción a JPA con Spring Boot 3",
            "JPA (Jakarta Persistence API) es el estándar de Java para persistencia de datos...\n" +
            "En este artículo exploramos cómo configurar JPA con Spring Boot 3 y MySQL...",
            "Aprende los fundamentos de JPA y cómo integrarlo con Spring Boot 3",
            ana
        );
        post1.publicar();

        Post post2 = crearPost(
            "Relaciones @OneToOne en JPA — Guía completa",
            "La relación @OneToOne permite mapear dos entidades que se relacionan de forma unitaria...\n" +
            "En este post veremos el lado propietario, el lado inverso y las mejores prácticas...",
            "Todo sobre la relación uno a uno en JPA con ejemplos prácticos",
            ana
        );
        post2.publicar();

        Post post3 = crearPost(
            "@ManyToMany con atributos extra — Entidad Intermedia",
            "Cuando la tabla de unión de una relación N:M necesita columnas extra...\n" +
            "la solución es crear una entidad intermedia con @EmbeddedId y @MapsId...",
            "Cómo manejar @ManyToMany cuando la join table tiene atributos propios",
            luis
        );
        post3.publicar();

        Post post4 = crearPost(
            "Borrador: Optimización de consultas con JOIN FETCH",
            "El problema N+1 es uno de los más comunes en aplicaciones JPA...",
            "Cómo usar JOIN FETCH para optimizar queries y evitar N+1",
            luis
        );
        // post4 queda como BORRADOR

        // 4. Relacionar posts con etiquetas (@ManyToMany)
        post1.agregarEtiqueta(java);
        post1.agregarEtiqueta(spring);
        post1.agregarEtiqueta(jpa);

        post2.agregarEtiqueta(java);
        post2.agregarEtiqueta(jpa);

        post3.agregarEtiqueta(jpa);
        post3.agregarEtiqueta(mysql);

        post4.agregarEtiqueta(jpa);
        post4.agregarEtiqueta(spring);

        // 5. Guardar todo
        postRepo.saveAll(List.of(post1, post2, post3, post4));

        log.info("=== Datos inicializados ===");
        log.info("Usuarios: {}", usuarioRepo.count());
        log.info("Posts: {}", postRepo.count());
        log.info("Etiquetas: {}", etiquetaRepo.count());
    }

    private Etiqueta crearEtiqueta(String nombre, String color) {
        return etiquetaRepo.save(
            Etiqueta.builder().nombre(nombre).color(color).build()
        );
    }

    private Usuario crearUsuario(String nombre, String email, String pass,
                                  String bio, String foto, String web, String ubicacion) {
        Perfil perfil = Perfil.builder()
            .bio(bio).fotoUrl(foto).sitioWeb(web).ubicacion(ubicacion)
            .build();
        Usuario usuario = Usuario.builder()
            .nombre(nombre).email(email).password(pass)
            .build();
        usuario.asignarPerfil(perfil);
        return usuarioRepo.save(usuario);
    }

    private Post crearPost(String titulo, String contenido, String resumen, Usuario autor) {
        Post post = Post.builder()
            .titulo(titulo).contenido(contenido).resumen(resumen)
            .build();
        autor.agregarPost(post);
        return post;
    }
}
