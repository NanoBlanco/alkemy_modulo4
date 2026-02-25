package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.UsuarioServicio;

import java.io.IOException;

import dao.UserDAO;

/**
 * Servlet implementation class DepositoServlet
 */
@WebServlet(name = "deposito", urlPatterns = { "/deposito" })
public class DepositoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final UserDAO dao = new UserDAO();
	private final UsuarioServicio us = new UsuarioServicio(dao);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositoServlet() {
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
	    
		request.getRequestDispatcher("deposito.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		Usuario u = (Usuario) session.getAttribute("usuario");

		int monto = Integer.parseInt(request.getParameter("monto"));
		String moneda = request.getParameter("moneda");
		
		us.depositoUsuario(u, moneda, monto);
	    response.sendRedirect("index.jsp");
	}

}
