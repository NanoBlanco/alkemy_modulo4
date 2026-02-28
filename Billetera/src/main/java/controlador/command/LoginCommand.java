package controlador.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.removeAttribute("errores");
		request.removeAttribute("error");
		
		HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.removeAttribute("errores");
	        session.removeAttribute("error");
	    }
		 return "login.jsp";
	}

}
