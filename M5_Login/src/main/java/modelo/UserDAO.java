package modelo;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	public UserDAO() {
		usuarios.add(new Usuario(
				1,"admin","1234",
				"Administrador",
				"admin@mail.com",
				"ADMIN"
				));
	}
	
	public Usuario buscarUsuario(String username) {
		return usuarios.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst()
				.orElse(null);
	}
}
