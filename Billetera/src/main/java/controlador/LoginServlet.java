package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.UsuarioServicio;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioServicio us = new UsuarioServicio();
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String user = request.getParameter("user").trim();
			String pass = request.getParameter("pass");
			
			// Validación de formato (SIEMPRE en servidor)
			List<String> errores = validarCredenciales(user, pass);
			
			if(!errores.isEmpty()) {
				request.setAttribute("errores", errores);
				request.setAttribute("username", user);
				request.getRequestDispatcher("/index.jsp?mostrar=login").forward(request, response);
				return;
			}
			
			// Validación de credencial contra BD
			Usuario u = us.login(user, pass);
			
			if(u == null) {
				request.setAttribute("error", "Credenciales Incorrectas");
				request.getRequestDispatcher("/index.jsp?mostrar=login").forward(request, response);
				return;
			}
			
			// Login exitoso
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", u);
			Cookie cookie = new Cookie("usuario",user);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			response.sendRedirect("index.jsp");
			
		}catch(Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

	private List<String> validarCredenciales(String user, String pass) {
		
		List<String> errores = new ArrayList<>();
		
		if(user.length() < 5 || user.length() > 20) {
			errores.add("El usuario debe tener entre 5 y 20 caracteres");
		}
		
		if(!user.matches("^[a-zA-Z0-9_]+$")) {
			errores.add("El usuario solo puede contener letras, numeros y guión bajo");
		}
		
		if(pass.length() < 4) {
			errores.add("La contraseña debe tener al menos 4 caracteres");
		}
		
		if(!pass.matches(".*[A-Z].*") || !pass.matches(".*[0-9]*.")) {
			errores.add("La contraseña debe contener al menos una mayúscula y un número.");
		}
		return errores;
	}

}
