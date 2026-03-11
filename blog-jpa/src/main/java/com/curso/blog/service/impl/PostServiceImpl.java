package com.curso.blog.service.impl;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.entity.*;
import com.curso.blog.exception.BlogExceptions.*;
import com.curso.blog.mapper.BlogMapper;
import com.curso.blog.repository.*;
import com.curso.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository       postRepo;
    private final UsuarioRepository    usuarioRepo;
    private final EtiquetaRepository   etiquetaRepo;
    private final PostEtiquetaRepository postEtiquetaRepo;
    private final BlogMapper           mapper;

    // ================================================================
    // CREAR
    // ================================================================
    @Override
    @Transactional
    public PostResponse crearPost(CrearPostRequest request) {
        log.debug("Creando post: {}", request.getTitulo());

        // 1. Obtener el autor
        Usuario autor = usuarioRepo.findById(request.getAutorId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", request.getAutorId()));

        if (!autor.getActivo()) {
            throw new OperacionInvalidaException("El autor está inactivo y no puede publicar posts");
        }

        // 2. Mapear DTO → Entidad Post
        Post post = mapper.toPost(request);

        // 3. Asignar autor usando el helper method del lado @OneToMany
        autor.agregarPost(post);  // mantiene coherencia bidireccional

        // 4. Asociar etiquetas si vienen en el request
        if (request.getEtiquetaIds() != null && !request.getEtiquetaIds().isEmpty()) {
            List<Etiqueta> etiquetas = etiquetaRepo.findAllById(request.getEtiquetaIds());
            etiquetas.forEach(post::agregarEtiqueta);  // helper method de Post
        }

        // 5. Guardar (cascade desde Usuario guarda el Post)
        Post guardado = postRepo.save(post);
        log.info("Post creado con id: {}", guardado.getId());

        return mapper.toPostResponse(guardado);
    }

    // ================================================================
    // LECTURA
    // ================================================================
    @Override
    @Transactional(readOnly = true)
    public PostResponse obtenerPorId(Long id) {
        Post post = postRepo.findByIdWithAutorAndEtiquetas(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Post", id));
        return mapper.toPostResponse(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResumenResponse> listarPublicados() {
        return postRepo.findPublicadosWithAutor()
            .stream()
            .map(mapper::toPostResumen)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PaginaResponse<PostResumenResponse> listarPublicadosPaginado(Pageable pageable) {
        Page<Post> pagina = postRepo.findByEstado(Post.EstadoPost.PUBLICADO, pageable);
        List<PostResumenResponse> contenido = pagina.getContent()
            .stream()
            .map(mapper::toPostResumen)
            .collect(Collectors.toList());

        return PaginaResponse.<PostResumenResponse>builder()
            .contenido(contenido)
            .pagina(pagina.getNumber())
            .tamano(pagina.getSize())
            .totalElementos(pagina.getTotalElements())
            .totalPaginas(pagina.getTotalPages())
            .ultima(pagina.isLast())
            .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResumenResponse> listarPorAutor(Long autorId) {
        return postRepo.findByAutorIdWithEtiquetas(autorId)
            .stream()
            .map(mapper::toPostResumen)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResumenResponse> buscarPorTitulo(String titulo) {
        return postRepo.findByTituloContainingIgnoreCase(titulo)
            .stream()
            .map(mapper::toPostResumen)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResumenResponse> listarPorEtiqueta(String nombreEtiqueta) {
        return postRepo.findByEtiquetaNombre(nombreEtiqueta)
            .stream()
            .map(mapper::toPostResumen)
            .collect(Collectors.toList());
    }

    // ================================================================
    // ACTUALIZAR
    // ================================================================
    @Override
    @Transactional
    public PostResponse actualizarPost(Long id, ActualizarPostRequest request) {
        Post post = postRepo.findByIdWithAutorAndEtiquetas(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Post", id));

        if (request.getTitulo()   != null) post.setTitulo(request.getTitulo());
        if (request.getContenido()!= null) post.setContenido(request.getContenido());
        if (request.getResumen()  != null) post.setResumen(request.getResumen());

        // Reemplazar conjunto de etiquetas si se envía
        if (request.getEtiquetaIds() != null) {
            // Limpiar etiquetas actuales
            Set<Etiqueta> actuales = Set.copyOf(post.getEtiquetas());
            actuales.forEach(post::removerEtiqueta);

            // Agregar nuevas
            List<Etiqueta> nuevas = etiquetaRepo.findAllById(request.getEtiquetaIds());
            nuevas.forEach(post::agregarEtiqueta);
        }

        return mapper.toPostResponse(post);
        // JPA dirty-checking detecta cambios → UPDATE automático al cerrar la transacción
    }

    @Override
    @Transactional
    public PostResponse publicarPost(Long id) {
        Post post = postRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Post", id));

        if (post.getEstado() == Post.EstadoPost.PUBLICADO) {
            throw new OperacionInvalidaException("El post ya está publicado");
        }

        post.publicar();  // cambia estado + asigna fechaPublicacion
        log.info("Post {} publicado", id);
        return mapper.toPostResponse(post);
    }

    @Override
    @Transactional
    public PostResponse cambiarEstado(Long id, Post.EstadoPost estado) {
        Post post = postRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Post", id));
        post.setEstado(estado);
        return mapper.toPostResponse(post);
    }

    // ================================================================
    // ELIMINAR
    // ================================================================
    @Override
    @Transactional
    public void eliminarPost(Long id) {
        Post post = postRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Post", id));
        postRepo.delete(post);
        log.info("Post {} eliminado", id);
    }

    // ================================================================
    // GESTIÓN DE ETIQUETAS
    // ================================================================
    @Override
    @Transactional
    public PostResponse agregarEtiqueta(Long postId, Long etiquetaId) {
        Post post = postRepo.findByIdWithAutorAndEtiquetas(postId)
            .orElseThrow(() -> new RecursoNoEncontradoException("Post", postId));

        Etiqueta etiqueta = etiquetaRepo.findById(etiquetaId)
            .orElseThrow(() -> new RecursoNoEncontradoException("Etiqueta", etiquetaId));

        post.agregarEtiqueta(etiqueta);

        // También registrar en la entidad intermedia con atributos extra
        if (!postEtiquetaRepo.existsByPostIdAndEtiquetaId(postId, etiquetaId)) {
            PostEtiqueta pe = PostEtiqueta.builder()
                .post(post)
                .etiqueta(etiqueta)
                .id(new PostEtiquetaId(postId, etiquetaId))
                .build();
            postEtiquetaRepo.save(pe);
        }

        return mapper.toPostResponse(post);
    }

    @Override
    @Transactional
    public PostResponse removerEtiqueta(Long postId, Long etiquetaId) {
        Post post = postRepo.findByIdWithAutorAndEtiquetas(postId)
            .orElseThrow(() -> new RecursoNoEncontradoException("Post", postId));

        Etiqueta etiqueta = etiquetaRepo.findById(etiquetaId)
            .orElseThrow(() -> new RecursoNoEncontradoException("Etiqueta", etiquetaId));

        post.removerEtiqueta(etiqueta);
        return mapper.toPostResponse(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostEtiquetaResponse> obtenerEtiquetasDetalle(Long postId) {
        if (!postRepo.existsById(postId)) {
            throw new RecursoNoEncontradoException("Post", postId);
        }
        return postEtiquetaRepo.findByPostIdWithEtiqueta(postId)
            .stream()
            .map(mapper::toPostEtiquetaResponse)
            .collect(Collectors.toList());
    }
}
