package controlador.command;

import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class GuardarUsuarioCommand implements Command {
	
	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nombre = request.getParameter("nombre").trim();
		String user = request.getParameter("user").trim();
		String pass = request.getParameter("pass");
		String rol = request.getParameter("rol");
		
		// Validación de formato (SIEMPRE en servidor)
		List<String> errores = validarCredenciales(nombre, user, pass);
		
		if(!errores.isEmpty()) {
			request.setAttribute("errores", errores);
			request.setAttribute("username", user);

			return "agregarUsuario.jsp";
		}
		
		if (rol.isEmpty()) rol = "USER";
		
		servicio.agregarUsuario(
				new Usuario(1,nombre,user,pass,rol));
		
		response.sendRedirect("app?action=listarUsuarios");
		return null;
	}
	
	private List<String> validarCredenciales(String nombre, String user, String pass) {
		
		List<String> errores = new ArrayList<>();
		
		if(nombre.length() < 5 || nombre.length() > 50) {
			errores.add("El nombre debe tener entre 5 y 50 caracteres");
		}
		
		if(!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+){0,9}$")) {
			errores.add("El nombre solo puede contener letras");
		}
		
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
