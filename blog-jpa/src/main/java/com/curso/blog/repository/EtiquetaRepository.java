package com.curso.blog.repository;

import com.curso.blog.entity.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {

    Optional<Etiqueta> findByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);

    List<Etiqueta> findByNombreIn(Set<String> nombres);

    /**
     * Etiquetas con sus posts — evita N+1 al listar.
     */
    @Query("SELECT DISTINCT e FROM Etiqueta e LEFT JOIN FETCH e.posts")
    List<Etiqueta> findAllWithPosts();

    /**
     * Etiquetas más usadas — Top N.
     */
    @Query("""
           SELECT e FROM Etiqueta e
           WHERE SIZE(e.posts) > 0
           ORDER BY SIZE(e.posts) DESC
           """)
    List<Etiqueta> findMasUsadas();

    /**
     * Etiquetas de un post específico.
     */
    @Query("""
           SELECT e FROM Etiqueta e
           JOIN e.posts p
           WHERE p.id = :postId
           """)
    List<Etiqueta> findByPostId(@Param("postId") Long postId);
}
