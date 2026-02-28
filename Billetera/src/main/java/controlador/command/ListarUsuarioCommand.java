package controlador.command;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.UsuarioServicio;

public class ListarUsuarioCommand implements Command {

	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("usuarios", servicio.getUsuarios());
		return  "usuarios.jsp";
	}

}
