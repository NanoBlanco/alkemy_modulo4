package controlador.command;

import java.util.List;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class ActualizarUsuarioCommand implements Command {

	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre").trim();
		String user = request.getParameter("user").trim();
		String pass = request.getParameter("pass");
		String rol = request.getParameter("rol");
		
		if (rol.isEmpty()) rol = "USER";
			
		List<String> errores = servicio.validarCredenciales(nombre, user, pass);
		
		if(!errores.isEmpty()) {
			request.setAttribute("errores", errores);
			request.setAttribute("username", user);
			return "agregarUsuario.jsp";
		}
					
		servicio.actualizar(new Usuario(
			id,nombre,user,pass,rol
			));
		
		response.sendRedirect("app?action=listarUsuarios");
		return null;
	}

}
