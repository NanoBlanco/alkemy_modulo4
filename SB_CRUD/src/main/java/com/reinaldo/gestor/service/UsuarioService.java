package com.reinaldo.gestor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reinaldo.gestor.exception.UserNotFoundException;
import com.reinaldo.gestor.model.Usuario;
import com.reinaldo.gestor.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	private final UsuarioRepository repo;
	
	public UsuarioService(UsuarioRepository repo) {
		this.repo = repo;
	}
	
	public List<Usuario> listarUsuarios(){
		return repo.findAllWithRoles();
	}
	
	public Usuario obtenerUsuario(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
	}

	public void createUsuario(Usuario u) {
		repo.save(u);
	}
	
	public void updateUsuario(Usuario u) {
		repo.update(u);
	}
	
	public void deleteUsuario(Long id) {
		repo.delete(id);
	}
}
