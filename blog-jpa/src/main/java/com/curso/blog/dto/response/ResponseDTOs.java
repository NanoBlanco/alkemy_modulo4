package com.curso.blog.dto.response;

import com.curso.blog.entity.Post;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * DTOs de salida (Response) — lo que el API devuelve al cliente.
 * Evitan exponer entidades JPA directamente (previene referencias circulares,
 * LazyInitializationException y campos innecesarios).
 */
public class ResponseDTOs {

    // =============================================
    // Perfil
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PerfilResponse {
        private Long    id;
        private String  bio;
        private String  fotoUrl;
        private String  sitioWeb;
        private String  ubicacion;
    }

    // =============================================
    // Usuario
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UsuarioResponse {
        private Long            id;
        private String          nombre;
        private String          email;
        private Boolean         activo;
        private LocalDateTime   fechaRegistro;
        private PerfilResponse  perfil;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UsuarioResumenResponse {
        private Long   id;
        private String nombre;
        private String email;
    }

    // =============================================
    // Etiqueta
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class EtiquetaResponse {
        private Long   id;
        private String nombre;
        private String color;
    }

    // =============================================
    // PostEtiqueta (relación con atributos extra)
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PostEtiquetaResponse {
        private EtiquetaResponse etiqueta;
        private LocalDate        fechaAplicacion;
        private String           notas;
    }

    // =============================================
    // Post
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PostResponse {
        private Long                  id;
        private String                titulo;
        private String                contenido;
        private String                resumen;
        private Post.EstadoPost       estado;
        private LocalDateTime         fechaCreacion;
        private LocalDateTime         fechaActualizacion;
        private LocalDateTime         fechaPublicacion;
        private UsuarioResumenResponse autor;
        private Set<EtiquetaResponse>  etiquetas;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PostResumenResponse {
        private Long                  id;
        private String                titulo;
        private String                resumen;
        private Post.EstadoPost       estado;
        private LocalDateTime         fechaPublicacion;
        private UsuarioResumenResponse autor;
        private Set<EtiquetaResponse>  etiquetas;
    }

    // =============================================
    // API response wrapper genérico
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ApiResponse<T> {
        private boolean success;
        private String  message;
        private T       data;

        public static <T> ApiResponse<T> ok(String message, T data) {
            return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
        }

        public static <T> ApiResponse<T> error(String message) {
            return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
        }
    }

    // =============================================
    // Paginación
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PaginaResponse<T> {
        private List<T> contenido;
        private int     pagina;
        private int     tamano;
        private long    totalElementos;
        private int     totalPaginas;
        private boolean ultima;
    }
}
