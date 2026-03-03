package controlador.command;

import java.util.List;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class GuardarRegistroCommand implements Command {

	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nombre = request.getParameter("nombre").trim();
		String user = request.getParameter("user").trim();
		String pass = request.getParameter("pass");
		
		// Validación de formato (SIEMPRE en servidor)
		List<String> errores = servicio.validarCredenciales(nombre, user, pass);
		
		if(!errores.isEmpty()) {
			request.setAttribute("errores", errores);
			request.setAttribute("username", user);
			
			return "registrar.jsp";
		}
		
		servicio.agregarUsuario(new Usuario(
			1,nombre,
			user,
			pass,
			"USER"
			));
		
		response.sendRedirect("login.jsp");
		return null;
	}

}
