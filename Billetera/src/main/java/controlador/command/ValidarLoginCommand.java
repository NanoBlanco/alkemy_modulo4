package controlador.command;

import java.util.List;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class ValidarLoginCommand implements Command {

	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String user = request.getParameter("user").trim();
		String pass = request.getParameter("pass");
		
		// Validación de formato (SIEMPRE en servidor)
		List<String> errores = servicio.validarCredenciales(user, pass);
		
		if(!errores.isEmpty()) {
			request.setAttribute("errores", errores);
			request.setAttribute("username", user);
			return "login.jsp";
		}
		
		// Validación de credencial contra BD
		Usuario u = servicio.login(user, pass);
		
		if(u == null) {
			request.setAttribute("error", "credenciales");
			return "login.jsp";
		}
		
		// Login exitoso
		HttpSession sesion = request.getSession();
		sesion.setAttribute("usuario", u);
		sesion.setMaxInactiveInterval(30 * 60);
		response.sendRedirect("index.jsp");
		return null;
	}

}
