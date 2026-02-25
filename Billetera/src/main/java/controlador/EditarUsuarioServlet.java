package controlador;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.UsuarioServicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;

/**
 * Servlet implementation class EditarUsuarioServlet
 */
@WebServlet(name = "editarUsuario", urlPatterns = { "/editarUsuario" })
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final UserDAO dao = new UserDAO();
	private final UsuarioServicio us = new UsuarioServicio(dao);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarUsuarioServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("usuario") == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
	    
	    int id = Integer.parseInt(request.getParameter("id"));
	    Usuario u = us.getUsuario(id);

	    request.setAttribute("usuarioEditar", u);
	    request.setAttribute("modo", "editar");

	    request.getRequestDispatcher("/agregarUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre").trim();
			String user = request.getParameter("user").trim();
			String pass = request.getParameter("pass");
			String rol = request.getParameter("rol");
			
			if(pass.isEmpty()) pass="1234A";
			if (rol.isEmpty()) rol = "USER";
				
			// Validación de formato (SIEMPRE en servidor)
			List<String> errores = validarCredenciales(nombre, user, pass);
			
			if(!errores.isEmpty()) {
				request.setAttribute("errores", errores);
				request.setAttribute("username", user);
				request.getRequestDispatcher("agregarUsuario.jsp").forward(request, response);
				return;
			}
						
			us.actualizar(new Usuario(
				id,nombre,user,pass,rol
				));
			
			response.sendRedirect("/Billetera/usuarios");
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
