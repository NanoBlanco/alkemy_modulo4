package controlador.command;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.UsuarioServicio;

public class EliminarUsuarioCommand implements Command {

	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		servicio.eliminar(id);
		
		response.sendRedirect("app?action=listarUsuarios");
		return null;
	}

}
