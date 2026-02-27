package controlador.command;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.ContactoDTO;
import modelo.service.ContactoService;
import modelo.service.ServiceFactory;

public class ListarContactoCommand implements Command {
	
	private ContactoService service = ServiceFactory.getContactoService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<ContactoDTO> lista = service.listarContactos();
		request.setAttribute("contactos", lista);
		
		return "contactos.jsp";
	}

}
