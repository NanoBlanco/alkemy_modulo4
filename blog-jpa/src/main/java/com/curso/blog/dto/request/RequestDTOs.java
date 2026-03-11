package com.curso.blog.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTOs de entrada (Request) — lo que el cliente envía al API.
 * Separar DTOs de las entidades evita exponer la estructura interna de BD.
 */
public class RequestDTOs {

    // =============================================
    // Usuario
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CrearUsuarioRequest {

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email no tiene un formato válido")
        @Size(max = 150)
        private String email;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        private String password;

        // Perfil inicial (opcional al crear usuario)
        private CrearPerfilRequest perfil;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ActualizarUsuarioRequest {

        @Size(min = 2, max = 100)
        private String nombre;

        @Email
        private String email;
    }

    // =============================================
    // Perfil
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CrearPerfilRequest {

        @Size(max = 2000, message = "La bio no puede superar los 2000 caracteres")
        private String bio;

        @Size(max = 500)
        private String fotoUrl;

        @Size(max = 200)
        private String sitioWeb;

        @Size(max = 100)
        private String ubicacion;
    }

    // =============================================
    // Post
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CrearPostRequest {

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 255)
        private String titulo;

        @NotBlank(message = "El contenido es obligatorio")
        private String contenido;

        @Size(max = 500)
        private String resumen;

        @NotNull(message = "El ID del autor es obligatorio")
        private Long autorId;

        // IDs de etiquetas a asociar
        private java.util.Set<Long> etiquetaIds = new java.util.HashSet<>();
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ActualizarPostRequest {

        @Size(max = 255)
        private String titulo;

        private String contenido;

        @Size(max = 500)
        private String resumen;

        private java.util.Set<Long> etiquetaIds;
    }

    // =============================================
    // Etiqueta
    // =============================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CrearEtiquetaRequest {

        @NotBlank(message = "El nombre de la etiqueta es obligatorio")
        @Size(min = 2, max = 50)
        private String nombre;

        @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "El color debe ser un hex válido, ej: #FF5733")
        private String color;
    }
}
