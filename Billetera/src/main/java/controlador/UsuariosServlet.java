package controlador;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.UsuarioServicio;

/**
 * Servlet implementation class UsuariosServlet
 */
//@WebServlet(name = "usuarios", urlPatterns = { "/usuarios" })
public class UsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserDAO dao = new UserDAO();
	private final UsuarioServicio us = new UsuarioServicio(dao);
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("usuarios", us.getUsuarios());
		request.getRequestDispatcher("usuarios.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
