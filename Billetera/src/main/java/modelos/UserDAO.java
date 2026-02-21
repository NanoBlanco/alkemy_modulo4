package modelos;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	public UserDAO() {
		usuarios.add(new Usuario(
				1,"1234A",
				"Administrador",
				"admin@mail.com",
				"ADMIN"
				));		
	}
	
	public Usuario buscarUsuario(String username) {
		return usuarios.stream()
				.filter(u -> u.getCorreo().equals(username))
				.findFirst()
				.orElse(null);
	}
	
	public Usuario buscarPorId(int id) {
		return usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst().orElse(null);
	}
	
	public List<Usuario> listar(){
		return usuarios;
	}

	public void guardar(Usuario u) {
		int id = usuarios.stream()
				.mapToInt(Usuario::getId)
				.max().orElse(0)+1;
		
		u.setId(id);
		usuarios.add(u);
	}
}
