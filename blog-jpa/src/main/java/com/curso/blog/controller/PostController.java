package com.curso.blog.controller;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.entity.Post;
import com.curso.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // POST /api/v1/posts
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> crearPost(
            @Valid @RequestBody CrearPostRequest request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Post creado", postService.crearPost(request)));
    }

    // GET /api/v1/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> obtenerPost(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("OK", postService.obtenerPorId(id)));
    }

    // GET /api/v1/posts?pagina=0&tamano=10
    @GetMapping
    public ResponseEntity<ApiResponse<PaginaResponse<PostResumenResponse>>> listarPublicados(
            @RequestParam(defaultValue = "0")  int pagina,
            @RequestParam(defaultValue = "10") int tamano) {
        Pageable pageable = PageRequest.of(pagina, tamano,
            Sort.by(Sort.Direction.DESC, "fechaPublicacion"));
        return ResponseEntity.ok(
            ApiResponse.ok("OK", postService.listarPublicadosPaginado(pageable)));
    }

    // GET /api/v1/posts/autor/{autorId}
    @GetMapping("/autor/{autorId}")
    public ResponseEntity<ApiResponse<List<PostResumenResponse>>> listarPorAutor(
            @PathVariable Long autorId) {
        return ResponseEntity.ok(
            ApiResponse.ok("OK", postService.listarPorAutor(autorId)));
    }

    // GET /api/v1/posts/buscar?titulo=spring
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<PostResumenResponse>>> buscarPorTitulo(
            @RequestParam String titulo) {
        return ResponseEntity.ok(
            ApiResponse.ok("OK", postService.buscarPorTitulo(titulo)));
    }

    // GET /api/v1/posts/etiqueta/{nombre}
    @GetMapping("/etiqueta/{nombre}")
    public ResponseEntity<ApiResponse<List<PostResumenResponse>>> listarPorEtiqueta(
            @PathVariable String nombre) {
        return ResponseEntity.ok(
            ApiResponse.ok("OK", postService.listarPorEtiqueta(nombre)));
    }

    // PUT /api/v1/posts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> actualizarPost(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarPostRequest request) {
        return ResponseEntity.ok(
            ApiResponse.ok("Post actualizado", postService.actualizarPost(id, request)));
    }

    // PATCH /api/v1/posts/{id}/publicar
    @PatchMapping("/{id}/publicar")
    public ResponseEntity<ApiResponse<PostResponse>> publicarPost(@PathVariable Long id) {
        return ResponseEntity.ok(
            ApiResponse.ok("Post publicado", postService.publicarPost(id)));
    }

    // PATCH /api/v1/posts/{id}/estado
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<PostResponse>> cambiarEstado(
            @PathVariable Long id,
            @RequestParam Post.EstadoPost estado) {
        return ResponseEntity.ok(
            ApiResponse.ok("Estado actualizado", postService.cambiarEstado(id, estado)));
    }

    // DELETE /api/v1/posts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPost(@PathVariable Long id) {
        postService.eliminarPost(id);
        return ResponseEntity.ok(ApiResponse.ok("Post eliminado", null));
    }

    // POST /api/v1/posts/{postId}/etiquetas/{etiquetaId}
    @PostMapping("/{postId}/etiquetas/{etiquetaId}")
    public ResponseEntity<ApiResponse<PostResponse>> agregarEtiqueta(
            @PathVariable Long postId,
            @PathVariable Long etiquetaId) {
        return ResponseEntity.ok(
            ApiResponse.ok("Etiqueta agregada", postService.agregarEtiqueta(postId, etiquetaId)));
    }

    // DELETE /api/v1/posts/{postId}/etiquetas/{etiquetaId}
    @DeleteMapping("/{postId}/etiquetas/{etiquetaId}")
    public ResponseEntity<ApiResponse<PostResponse>> removerEtiqueta(
            @PathVariable Long postId,
            @PathVariable Long etiquetaId) {
        return ResponseEntity.ok(
            ApiResponse.ok("Etiqueta removida", postService.removerEtiqueta(postId, etiquetaId)));
    }

    // GET /api/v1/posts/{postId}/etiquetas/detalle
    @GetMapping("/{postId}/etiquetas/detalle")
    public ResponseEntity<ApiResponse<List<PostEtiquetaResponse>>> obtenerEtiquetasDetalle(
            @PathVariable Long postId) {
        return ResponseEntity.ok(
            ApiResponse.ok("OK", postService.obtenerEtiquetasDetalle(postId)));
    }
}
