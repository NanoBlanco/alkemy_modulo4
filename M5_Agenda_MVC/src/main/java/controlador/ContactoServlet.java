package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.ContactoDTO;
import modelo.ContactoService;

/**
 * Servlet implementation class ContactoServlet
 */
@WebServlet(name = "contacto", urlPatterns = { "/contacto" })
public class ContactoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactoService cs = new ContactoService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion == null || accion.equals("listar")) {
			List<ContactoDTO> lista = cs.listarContactos();
			request.setAttribute("contactos", lista);
			request.getRequestDispatcher("contactos.jsp").forward(request, response);
		}else if(accion.equals("nuevo")) {
			request.setAttribute("modo", "crear");
			request.getRequestDispatcher("contacto-form.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContactoDTO c = new ContactoDTO();
		c.setNombre(request.getParameter("nombre"));
		c.setCorreo(request.getParameter("correo"));
		c.setTelefono(request.getParameter("telefono"));
		
		cs.guardarContacto(c);
		response.sendRedirect("contacto?accion=listar");
	}

}
