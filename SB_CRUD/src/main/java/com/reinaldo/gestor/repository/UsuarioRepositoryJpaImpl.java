package com.reinaldo.gestor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reinaldo.gestor.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Profile("jpa")
@Repository
@Transactional
public class UsuarioRepositoryJpaImpl implements UsuarioRepositoryJpa {

	@PersistenceContext
	private EntityManager entity;

	@Override
	public List<Usuario> findAll() {
		return entity
				.createQuery("SELECT u FROM Usuario u", Usuario.class)
				.getResultList();
	}
	
	@Override
	public Optional<Usuario> findById(Long id) {
		Usuario u = entity.find(Usuario.class, id);
		return Optional.ofNullable(u);
	}
	
	@Override
	public void save(Usuario u) {
		entity.persist(u);
	}

	@Override
	public void update(Usuario u) {
		entity.merge(u);
		
	}

	@Override
	public void delete(Long id) {
		entity.remove(id);
		
	}

	@Override
	public List<Usuario> findAllWithRoles() {
		return null;
	}
	
	
}

