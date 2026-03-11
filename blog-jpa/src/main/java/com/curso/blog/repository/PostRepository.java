package com.curso.blog.repository;

import com.curso.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // --- Derived Queries ---
    List<Post> findByAutorId(Long autorId);

    List<Post> findByEstado(Post.EstadoPost estado);

    Page<Post> findByEstado(Post.EstadoPost estado, Pageable pageable);

    List<Post> findByTituloContainingIgnoreCase(String titulo);

    // --- JPQL con JOIN FETCH para evitar N+1 ---

    /**
     * Post completo: autor + etiquetas en una sola consulta.
     */
    @Query("""
           SELECT DISTINCT p FROM Post p
           LEFT JOIN FETCH p.autor a
           LEFT JOIN FETCH p.etiquetas
           WHERE p.id = :id
           """)
    Optional<Post> findByIdWithAutorAndEtiquetas(@Param("id") Long id);

    /**
     * Todos los posts publicados con autor.
     */
    @Query("""
           SELECT p FROM Post p
           JOIN FETCH p.autor
           WHERE p.estado = 'PUBLICADO'
           ORDER BY p.fechaPublicacion DESC
           """)
    List<Post> findPublicadosWithAutor();

    /**
     * Posts de un autor específico con etiquetas.
     */
    @Query("""
           SELECT DISTINCT p FROM Post p
           LEFT JOIN FETCH p.etiquetas
           WHERE p.autor.id = :autorId
           ORDER BY p.fechaCreacion DESC
           """)
    List<Post> findByAutorIdWithEtiquetas(@Param("autorId") Long autorId);

    /**
     * Posts filtrados por nombre de etiqueta.
     */
    @Query("""
           SELECT DISTINCT p FROM Post p
           JOIN p.etiquetas e
           JOIN FETCH p.autor
           WHERE e.nombre = :nombreEtiqueta
           AND p.estado = 'PUBLICADO'
           """)
    List<Post> findByEtiquetaNombre(@Param("nombreEtiqueta") String nombreEtiqueta);

    /**
     * Actualización masiva: cambiar estado.
     * @Modifying + @Transactional requeridos para UPDATE/DELETE en JPQL.
     */
    @Modifying
    @Query("UPDATE Post p SET p.estado = :estado WHERE p.autor.id = :autorId")
    int actualizarEstadoPorAutor(
        @Param("autorId") Long autorId,
        @Param("estado")  Post.EstadoPost estado
    );

    /**
     * Contar posts por estado.
     */
    @Query("SELECT COUNT(p) FROM Post p WHERE p.estado = :estado")
    long contarPorEstado(@Param("estado") Post.EstadoPost estado);
}
