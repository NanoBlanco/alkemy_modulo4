package servicios;

import repositorio.UsuarioRepository;

public class UsuarioServicio {

	private UsuarioRepository repo;
	
	public UsuarioServicio(UsuarioRepository repo) {
		this.repo = repo;
	}
	
	public String obtenerNombre(int id) {
		return repo.buscarUsuario(id);
	}
}
