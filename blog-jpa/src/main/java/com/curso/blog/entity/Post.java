package com.curso.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"autor", "etiquetas", "postEtiquetas"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "contenido", nullable = false, columnDefinition = "LONGTEXT")
    private String contenido;

    @Column(name = "resumen", length = 500)
    private String resumen;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoPost estado = EstadoPost.BORRADOR;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    // ================================================================
    // RELACIÓN N:1  →  Post es el PROPIETARIO de la FK autor_id
    // Muchos posts pertenecen a un solo autor
    // ================================================================
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name        = "autor_id",
        nullable    = false,
        foreignKey  = @ForeignKey(name = "fk_post_autor")
    )
    private Usuario autor;

    // ================================================================
    // RELACIÓN N:M  →  Post es el PROPIETARIO (@JoinTable)
    // Relación simple sin atributos extra — para uso básico
    // ================================================================
    @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name               = "post_etiqueta",
        joinColumns        = @JoinColumn(
            name           = "post_id",
            foreignKey     = @ForeignKey(name = "fk_post_etiqueta_post")
        ),
        inverseJoinColumns = @JoinColumn(
            name           = "etiqueta_id",
            foreignKey     = @ForeignKey(name = "fk_post_etiqueta_etiqueta")
        )
    )
    @Builder.Default
    private Set<Etiqueta> etiquetas = new HashSet<>();

    // ================================================================
    // RELACIÓN N:M con atributos extra  →  entidad intermedia
    // PostEtiqueta registra fecha_aplicacion y notas
    // ================================================================
    @OneToMany(
        mappedBy      = "post",
        cascade       = CascadeType.ALL,
        orphanRemoval = true,
        fetch         = FetchType.LAZY
    )
    @Builder.Default
    private Set<PostEtiqueta> postEtiquetas = new HashSet<>();

    // ================================================================
    // Ciclo de vida JPA
    // ================================================================
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // ================================================================
    // Métodos helper — bidireccionalidad
    // ================================================================
    public void agregarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.add(etiqueta);
        etiqueta.getPosts().add(this);
    }

    public void removerEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.remove(etiqueta);
        etiqueta.getPosts().remove(this);
    }

    public void publicar() {
        this.estado = EstadoPost.PUBLICADO;
        this.fechaPublicacion = LocalDateTime.now();
    }

    // ================================================================
    // Enum de estado del Post
    // ================================================================
    public enum EstadoPost {
        BORRADOR, PUBLICADO, ARCHIVADO
    }
}
