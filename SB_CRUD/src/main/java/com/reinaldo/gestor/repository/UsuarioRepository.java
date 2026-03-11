package com.reinaldo.gestor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.reinaldo.gestor.model.Usuario;

public interface UsuarioRepository {
	
	List<Usuario> findAll();
	
	Optional<Usuario> findById(Long id);
	
	void save(Usuario u);
	
	void update(Usuario u);
	
	void delete(Long id);
	
	@Query("""
			SELECT u
			FROM Usuario u
			LEFT JOIN FETCH u.roles
			""")
	List<Usuario> findAllWithRoles();

}
