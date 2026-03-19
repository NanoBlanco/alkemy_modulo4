package com.curso.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "post_etiqueta_detalle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"post", "etiqueta"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostEtiqueta {

    // ================================================================
    // Clave primaria compuesta
    // ================================================================
    @EmbeddedId
    @EqualsAndHashCode.Include
    @Builder.Default
    private PostEtiquetaId id = new PostEtiquetaId();

    // ================================================================
    // @MapsId vincula la FK con el campo del @EmbeddedId
    // ================================================================
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")                          // Mapea PostEtiquetaId.postId
    @JoinColumn(
        name       = "post_id",
        nullable   = false,
        foreignKey = @ForeignKey(name = "fk_pe_detalle_post")
    )
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("etiquetaId")                      // Mapea PostEtiquetaId.etiquetaId
    @JoinColumn(
        name       = "etiqueta_id",
        nullable   = false,
        foreignKey = @ForeignKey(name = "fk_pe_detalle_etiqueta")
    )
    private Etiqueta etiqueta;

    // ================================================================
    // Atributos extra de la relación
    // ================================================================
    @Column(name = "fecha_aplicacion", nullable = false)
    @Builder.Default
    private LocalDate fechaAplicacion = LocalDate.now();

    @Column(name = "notas", length = 255)
    private String notas;

    // ================================================================
    // Constructor de conveniencia
    // ================================================================
    public PostEtiqueta(Post post, Etiqueta etiqueta, String notas) {
        this.post             = post;
        this.etiqueta         = etiqueta;
        this.notas            = notas;
        this.fechaAplicacion  = LocalDate.now();
        this.id               = new PostEtiquetaId(post.getId(), etiqueta.getId());
    }
}
