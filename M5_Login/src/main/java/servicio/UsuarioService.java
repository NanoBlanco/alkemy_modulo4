package servicio;

import modelo.UserDAO;
import modelo.Usuario;

public class UsuarioService {
	
	private UserDAO dao = new UserDAO();
	
	public Usuario login(String user, String pass) {
		
		Usuario u = dao.buscarUsuario(user);
		
		if (u!= null && u.getClave().equals(pass)) {
			return u;
		}
		
		return null;
	}

}
