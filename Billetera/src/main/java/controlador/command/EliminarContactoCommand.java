package controlador.command;

import dao.ContactoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servicios.ContactoServicio;

public class EliminarContactoCommand implements Command {

	private final ContactoServicio servicio = new ContactoServicio(new ContactoDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.removeAttribute("errores");


		HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.removeAttribute("errores");
	    }
	    
	    int id = Integer.parseInt(request.getParameter("id"));
	    servicio.eliminarContacto(id);
	    request.setAttribute("modo", "nuevo");
	    
		response.sendRedirect("/Billetera/app?action=contactos");
		return null;
	}

}
