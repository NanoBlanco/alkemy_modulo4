package controlador.command;

import java.util.ArrayList;
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
		List<String> errores = validarCredenciales(user, pass);
		
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

	private List<String> validarCredenciales(String user, String pass) {
		
		List<String> errores = new ArrayList<>();
		
		if(user.length() < 5 || user.length() > 20) {
			errores.add("El usuario debe tener entre 5 y 20 caracteres");
		}
		
		if(!user.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			errores.add("El usuario solo puede contener letras, numeros y guión bajo");
		}
		
		if(pass.length() < 5) {
			errores.add("La contraseña debe tener al menos 4 caracteres");
		}
		
		if(!pass.matches(".*[A-Z].*") || !pass.matches(".*[0-9].*")) {
			errores.add("La contraseña debe contener al menos una mayúscula y un número.");
		}
		return errores;
	}
}
