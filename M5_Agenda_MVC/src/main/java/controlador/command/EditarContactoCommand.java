package controlador.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.ContactoDTO;
import modelo.service.ContactoService;
import modelo.service.ServiceFactory;

public class EditarContactoCommand implements Command {
	
	private ContactoService service = ServiceFactory.getContactoService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		ContactoDTO c = service.obtenerContacto(id);
		
		request.setAttribute("modo", "editar");
		request.setAttribute("contacto", c);
		return "contacto-form.jsp";
	}

}
