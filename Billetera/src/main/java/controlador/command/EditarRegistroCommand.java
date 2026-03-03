package controlador.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;

public class EditarRegistroCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("usuario") == null) {
	    	return "login.jsp";
	        
	    }
	    
	    Usuario u = (Usuario) session.getAttribute("usuario");

	    request.setAttribute("usuarioEditar", u);
	    request.setAttribute("modo", "editar");

	    return "registrar.jsp";
	}

}
