package com.curso.blog.repository;

import com.curso.blog.entity.PostEtiqueta;
import com.curso.blog.entity.PostEtiquetaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostEtiquetaRepository extends JpaRepository<PostEtiqueta, PostEtiquetaId> {

    List<PostEtiqueta> findByPostId(Long postId);

    List<PostEtiqueta> findByEtiquetaId(Long etiquetaId);

    boolean existsByPostIdAndEtiquetaId(Long postId, Long etiquetaId);

    /**
     * Relaciones de un post con sus etiquetas completas y atributos extra.
     */
    @Query("""
           SELECT pe FROM PostEtiqueta pe
           JOIN FETCH pe.etiqueta
           WHERE pe.post.id = :postId
           ORDER BY pe.fechaAplicacion DESC
           """)
    List<PostEtiqueta> findByPostIdWithEtiqueta(@Param("postId") Long postId);
}
