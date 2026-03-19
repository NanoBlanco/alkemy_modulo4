package com.curso.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "etiquetas",
       uniqueConstraints = @UniqueConstraint(name = "uk_etiqueta_nombre", columnNames = "nombre"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "posts")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "color", length = 7)   // Ej: #FF5733
    private String color;

    // ================================================================
    // RELACIÓN N:M  →  Etiqueta es el lado INVERSO
    // mappedBy apunta al campo "etiquetas" definido en Post
    // ================================================================
    @ManyToMany(
        mappedBy = "etiquetas",
        fetch    = FetchType.LAZY
    )
    @Builder.Default
    private Set<Post> posts = new HashSet<>();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Etiqueta other = (Etiqueta) o;

        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    public void agregarPost(Post post) {
        posts.add(post);
        post.getEtiquetas().add(this);
    }
}
