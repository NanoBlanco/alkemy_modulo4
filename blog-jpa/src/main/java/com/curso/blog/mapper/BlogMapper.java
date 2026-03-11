package com.curso.blog.mapper;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Mapper manual — convierte entre Entidades y DTOs.
 *
 * Se hace manual (sin MapStruct) para que los estudiantes
 * entiendan exactamente qué campos se mapean y por qué.
 */
@Component
public class BlogMapper {

    // ==================== USUARIO ====================

    public UsuarioResponse toUsuarioResponse(Usuario u) {
        if (u == null) return null;
        return UsuarioResponse.builder()
            .id(u.getId())
            .nombre(u.getNombre())
            .email(u.getEmail())
            .activo(u.getActivo())
            .fechaRegistro(u.getFechaRegistro())
            .perfil(u.getPerfil() != null ? toPerfilResponse(u.getPerfil()) : null)
            .build();
    }

    public UsuarioResumenResponse toUsuarioResumen(Usuario u) {
        if (u == null) return null;
        return UsuarioResumenResponse.builder()
            .id(u.getId())
            .nombre(u.getNombre())
            .email(u.getEmail())
            .build();
    }

    public Usuario toUsuario(CrearUsuarioRequest req) {
        Usuario u = Usuario.builder()
            .nombre(req.getNombre())
            .email(req.getEmail())
            .password(req.getPassword())  // En prod: encriptar con BCrypt
            .build();
        if (req.getPerfil() != null) {
            Perfil p = toPerfil(req.getPerfil());
            u.asignarPerfil(p);
        }
        return u;
    }

    // ==================== PERFIL ====================

    public PerfilResponse toPerfilResponse(Perfil p) {
        if (p == null) return null;
        return PerfilResponse.builder()
            .id(p.getId())
            .bio(p.getBio())
            .fotoUrl(p.getFotoUrl())
            .sitioWeb(p.getSitioWeb())
            .ubicacion(p.getUbicacion())
            .build();
    }

    public Perfil toPerfil(CrearPerfilRequest req) {
        return Perfil.builder()
            .bio(req.getBio())
            .fotoUrl(req.getFotoUrl())
            .sitioWeb(req.getSitioWeb())
            .ubicacion(req.getUbicacion())
            .build();
    }

    // ==================== POST ====================

    public PostResponse toPostResponse(Post p) {
        if (p == null) return null;
        return PostResponse.builder()
            .id(p.getId())
            .titulo(p.getTitulo())
            .contenido(p.getContenido())
            .resumen(p.getResumen())
            .estado(p.getEstado())
            .fechaCreacion(p.getFechaCreacion())
            .fechaActualizacion(p.getFechaActualizacion())
            .fechaPublicacion(p.getFechaPublicacion())
            .autor(toUsuarioResumen(p.getAutor()))
            .etiquetas(p.getEtiquetas() == null ? new HashSet<>() :
                p.getEtiquetas().stream()
                    .map(this::toEtiquetaResponse)
                    .collect(Collectors.toSet()))
            .build();
    }

    public PostResumenResponse toPostResumen(Post p) {
        if (p == null) return null;
        return PostResumenResponse.builder()
            .id(p.getId())
            .titulo(p.getTitulo())
            .resumen(p.getResumen())
            .estado(p.getEstado())
            .fechaPublicacion(p.getFechaPublicacion())
            .autor(toUsuarioResumen(p.getAutor()))
            .etiquetas(p.getEtiquetas() == null ? new HashSet<>() :
                p.getEtiquetas().stream()
                    .map(this::toEtiquetaResponse)
                    .collect(Collectors.toSet()))
            .build();
    }

    public Post toPost(CrearPostRequest req) {
        return Post.builder()
            .titulo(req.getTitulo())
            .contenido(req.getContenido())
            .resumen(req.getResumen())
            .build();
    }

    // ==================== ETIQUETA ====================

    public EtiquetaResponse toEtiquetaResponse(Etiqueta e) {
        if (e == null) return null;
        return EtiquetaResponse.builder()
            .id(e.getId())
            .nombre(e.getNombre())
            .color(e.getColor())
            .build();
    }

    public Etiqueta toEtiqueta(CrearEtiquetaRequest req) {
        return Etiqueta.builder()
            .nombre(req.getNombre().toLowerCase().trim())
            .color(req.getColor())
            .build();
    }

    // ==================== POST ETIQUETA ====================

    public PostEtiquetaResponse toPostEtiquetaResponse(PostEtiqueta pe) {
        if (pe == null) return null;
        return PostEtiquetaResponse.builder()
            .etiqueta(toEtiquetaResponse(pe.getEtiqueta()))
            .fechaAplicacion(pe.getFechaAplicacion())
            .notas(pe.getNotas())
            .build();
    }
}
