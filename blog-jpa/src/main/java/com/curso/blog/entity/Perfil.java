package com.curso.blog.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad Perfil — tabla: perfiles
 *
 * Relaciones:
 *  - @OneToOne con Usuario  (lado PROPIETARIO — tiene la FK usuario_id)
 *
 * Regla: un Perfil no puede existir sin un Usuario.
 */
@Entity
@Table(name = "perfiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Column(name = "sitio_web", length = 200)
    private String sitioWeb;

    @Column(name = "ubicacion", length = 100)
    private String ubicacion;

    // ================================================================
    // RELACIÓN 1:1  →  Perfil es el PROPIETARIO (tiene la FK)
    // @JoinColumn define la columna FK en la tabla perfiles
    // ================================================================
    @OneToOne(
        fetch    = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name               = "usuario_id",   // Columna FK en tabla perfiles
        nullable           = false,
        unique             = true,           // Garantiza la unicidad 1:1
        foreignKey         = @ForeignKey(name = "fk_perfil_usuario")
    )
    private Usuario usuario;
}
