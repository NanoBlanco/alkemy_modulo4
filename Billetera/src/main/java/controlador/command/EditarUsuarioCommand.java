package controlador.command;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class EditarUsuarioCommand implements Command {

	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("usuario") == null) {
	    	return"login.jsp";
	    }
	    
	    int id = Integer.parseInt(request.getParameter("id"));
	    Usuario u = servicio.getUsuario(id);

	    request.setAttribute("usuarioEditar", u);
	    request.setAttribute("modo", "editar");

	    return "agregarUsuario.jsp";
		 
	}

}
