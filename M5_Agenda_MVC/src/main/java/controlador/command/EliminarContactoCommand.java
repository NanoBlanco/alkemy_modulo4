package controlador.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.service.ContactoService;
import modelo.service.ServiceFactory;

public class EliminarContactoCommand implements Command {

	private ContactoService service = ServiceFactory.getContactoService();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		service.eliminarContacto(id);
		
		response.sendRedirect("app?action=listar");
		return null;
	}

}
