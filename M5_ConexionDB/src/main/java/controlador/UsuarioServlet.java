package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet(name = "usuario", urlPatterns = { "/usuario" })
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UsuarioDAO dao = new UsuarioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> usuarios;
		try {
			usuarios = dao.listar();
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("usuarios.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario u = (Usuario) request.getAttribute("usuario");
		dao.actualizar(u);
	}

}
