package controlador.command;

import java.util.List;

import dao.ContactoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Contacto;
import servicios.ContactoServicio;

public class ActualizarContactoCommand implements Command {

	private final ContactoServicio servicio = new ContactoServicio(new ContactoDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre").trim();
		String correo = request.getParameter("correo").trim();
		
		// Validación de formato (SIEMPRE en servidor)
		List<String> errores = servicio.validarCredenciales(nombre, correo);
		
		if(!errores.isEmpty()) {
			request.setAttribute("errores", errores);
			request.setAttribute("nombre", nombre);
			return "contactos.jsp";
		}
		
		servicio.actualizarContacto(new Contacto(
				id,
				nombre,
				correo
		));
		
		response.sendRedirect("/Billetera/app?action=contactos");
		return null;
	}

}
