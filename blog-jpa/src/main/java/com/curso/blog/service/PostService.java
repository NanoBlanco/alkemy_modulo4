package com.curso.blog.service;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostResponse            crearPost(CrearPostRequest request);
    PostResponse            obtenerPorId(Long id);
    List<PostResumenResponse> listarPublicados();
    PaginaResponse<PostResumenResponse> listarPublicadosPaginado(Pageable pageable);
    List<PostResumenResponse> listarPorAutor(Long autorId);
    List<PostResumenResponse> buscarPorTitulo(String titulo);
    List<PostResumenResponse> listarPorEtiqueta(String nombreEtiqueta);
    PostResponse            actualizarPost(Long id, ActualizarPostRequest request);
    PostResponse            publicarPost(Long id);
    PostResponse            cambiarEstado(Long id, Post.EstadoPost estado);
    void                    eliminarPost(Long id);
    PostResponse            agregarEtiqueta(Long postId, Long etiquetaId);
    PostResponse            removerEtiqueta(Long postId, Long etiquetaId);
    List<PostEtiquetaResponse> obtenerEtiquetasDetalle(Long postId);
}
