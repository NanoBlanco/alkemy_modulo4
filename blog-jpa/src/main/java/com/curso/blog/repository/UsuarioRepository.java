package com.curso.blog.repository;

import com.curso.blog.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // --- Derived Queries (Spring genera el SQL automáticamente) ---
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByActivoTrue();

    // --- JPQL con JOIN FETCH (evita el problema N+1) ---

    /**
     * Carga el usuario junto con su perfil en UNA sola consulta SQL.
     * Sin JOIN FETCH se generarían 2 queries (N+1 problem).
     */
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.perfil WHERE u.id = :id")
    Optional<Usuario> findByIdWithPerfil(@Param("id") Long id);

    /**
     * Carga todos los usuarios con sus posts en una consulta.
     * DISTINCT evita duplicados en el resultado.
     */
    @Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.posts WHERE u.activo = true")
    List<Usuario> findAllActivosWithPosts();

    /**
     * Carga usuario + perfil + posts en una sola operación.
     */
    @Query("""
           SELECT DISTINCT u FROM Usuario u
           LEFT JOIN FETCH u.perfil
           LEFT JOIN FETCH u.posts p
           WHERE u.id = :id
           """)
    Optional<Usuario> findByIdWithPerfilAndPosts(@Param("id") Long id);
}
