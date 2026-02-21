package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.UserDAO;
import servicios.UsuarioServicio;

import java.io.IOException;

/**
 * Servlet implementation class InicioServlet
 */
@WebServlet(name = "inicio", urlPatterns = { "/inicio" })
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		UserDAO dao = new UserDAO();
		UsuarioServicio us = new UsuarioServicio(dao);
		
		getServletContext().setAttribute("servicio", us);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InicioServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
