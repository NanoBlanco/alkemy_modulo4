package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controlador.command.Command;
import controlador.command.CommandFactory;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(name = "app", urlPatterns = { "/app" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		Command command = CommandFactory.getCommand(action);
		
		try {
			String vista = command.execute(request, response);
			if(vista != null) request.getRequestDispatcher(vista).forward(request, response);
		}catch(Exception e) {
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	

}
