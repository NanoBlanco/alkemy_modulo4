package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.UsuarioServicio;

import java.io.IOException;

/**
 * Servlet implementation class UsuariosServlet
 */
@WebServlet(name = "usuarios", urlPatterns = { "/usuarios" })
public class UsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioServicio us = new UsuarioServicio();
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
