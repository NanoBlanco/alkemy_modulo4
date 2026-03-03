package controlador.command;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class DepositoNuevoCommand implements Command {

	private final UsuarioServicio servicio = new UsuarioServicio(new UserDAO());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		
		Usuario u = (Usuario) session.getAttribute("usuario");

		int monto = Integer.parseInt(request.getParameter("monto"));
		String moneda = request.getParameter("moneda");
		
		servicio.depositoUsuario(u, moneda, monto);
	    response.sendRedirect("index.jsp");
		return null;
	}

}
