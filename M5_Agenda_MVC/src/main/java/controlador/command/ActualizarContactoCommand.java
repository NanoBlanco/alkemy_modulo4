package controlador.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.ContactoDTO;
import modelo.service.ContactoService;
import modelo.service.ServiceFactory;

public class ActualizarContactoCommand implements Command {

	private ContactoService service = ServiceFactory.getContactoService();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ContactoDTO c = new ContactoDTO();
		c.setId(Integer.parseInt(request.getParameter("id")));
		c.setNombre(request.getParameter("nombre"));
		c.setCorreo(request.getParameter("correo"));
		c.setTelefono(request.getParameter("telefono"));
		
		service.actualizaContacto(c);
		response.sendRedirect("app?action=listar");
		return null;
	}

}
