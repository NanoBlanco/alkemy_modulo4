package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.UsuarioServicio;

/**
 * Servlet implementation class RegistrarServlet
 */
@WebServlet(name = "registrar", urlPatterns = { "/registrar" })
public class RegistrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsuarioServicio us;
    
	@Override
	public void init() throws ServletException {
		us = (UsuarioServicio) getServletContext().getAttribute("servicio");
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.removeAttribute("errores");
		
		HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.removeAttribute("errores");
	    }
	    request.setAttribute("modo", "crear");
		request.getRequestDispatcher("registrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nombre = request.getParameter("nombre").trim();
			String user = request.getParameter("user").trim();
			String pass = request.getParameter("pass");
			
			// Validación de formato (SIEMPRE en servidor)
			List<String> errores = validarCredenciales(nombre, user, pass);
			
			if(!errores.isEmpty()) {
				request.setAttribute("errores", errores);
				request.setAttribute("username", user);
				request.getRequestDispatcher("registrar.jsp").forward(request, response);
				return;
			}
			
			us.agregarUsuario(new Usuario(
				1,pass,
				nombre,
				user,
				"USER"
				));
			
			response.sendRedirect("login.jsp");
		}catch(Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
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
