package controlador.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.ContactoDTO;
import modelo.service.ContactoService;
import modelo.service.ServiceFactory;

public class GuardarContactoCommand implements Command {

	private ContactoService service = ServiceFactory.getContactoService();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ContactoDTO c = new ContactoDTO();
		c.setNombre(request.getParameter("nombre"));
		c.setCorreo(request.getParameter("correo"));
		c.setTelefono(request.getParameter("telefono"));
		
		service.guardarContacto(c);
		
		// PRG pattern
		response.sendRedirect("app?action=listar");
		return null;
	}

}
