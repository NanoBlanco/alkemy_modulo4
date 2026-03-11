package com.reinaldo.gestor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.reinaldo.gestor.mapper.UserMapper;
import com.reinaldo.gestor.model.Usuario;

@Profile("mybatis")
@Repository
public class UsuarioRepositoryMyBatis implements UsuarioRepository {

	private final UserMapper mapper;
	
	public UsuarioRepositoryMyBatis(UserMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<Usuario> findAll() {
		return mapper.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return Optional.ofNullable(mapper.findById(id));
	}

	@Override
	public void save(Usuario u) {
		mapper.save(u);
	}

	@Override
	public void update(Usuario u) {
		mapper.update(u);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public List<Usuario> findAllWithRoles() {
		return null;
	}

}
