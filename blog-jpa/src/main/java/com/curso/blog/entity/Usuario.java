package com.curso.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios",
       uniqueConstraints = @UniqueConstraint(name = "uk_usuario_email", columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"perfil", "posts"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "activo", nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    // ================================================================
    // RELACIÓN 1:1  →  Usuario ←→ Perfil
    // El lado inverso: mappedBy indica que Perfil es el propietario de la FK
    // ================================================================
    @OneToOne(
        mappedBy       = "usuario",
        cascade        = CascadeType.ALL,
        fetch          = FetchType.LAZY,
        optional       = false,
        orphanRemoval  = true
    )
    private Perfil perfil;

    // ================================================================
    // RELACIÓN 1:N  →  Usuario (1) ←→ Post (N)
    // mappedBy indica que Post es el propietario de la FK (autor_id)
    // ================================================================
    @OneToMany(
        mappedBy      = "autor",
        cascade       = CascadeType.ALL,
        fetch         = FetchType.LAZY,
        orphanRemoval = true
    )
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    // ================================================================
    // Ciclo de vida
    // ================================================================
    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // ================================================================
    // Métodos helper — mantienen coherencia bidireccional en memoria
    // ================================================================
    public void asignarPerfil(Perfil perfil) {
        this.perfil = perfil;
        perfil.setUsuario(this);
    }

    public void agregarPost(Post post) {
        this.posts.add(post);
        post.setAutor(this);
    }

    public void removerPost(Post post) {
        this.posts.remove(post);
        post.setAutor(null);
    }
}
