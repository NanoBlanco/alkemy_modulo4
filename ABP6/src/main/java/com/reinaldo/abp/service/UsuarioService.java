package com.reinaldo.abp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reinaldo.abp.entity.Usuario;
import com.reinaldo.abp.exception.RecursoNoEncontradoException;
import com.reinaldo.abp.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repo;
	
	public List<Usuario> listar() {
		return repo.findAll();
	}
	
	public Usuario obtener(Long id) {
		return repo.findById(id)
				.orElseThrow(()-> new RecursoNoEncontradoException("Usuario no Encontrado"));
	}
	
	public Usuario crear(Usuario u) {
		return repo.save(u);
	}
	
	public Usuario actualizar(Long id, Usuario u) {
		u.setId(id);
		return repo.save(u);
	}
	
	public void eliminar(Usuario p) {
		repo.delete(p);
	}
	
}
