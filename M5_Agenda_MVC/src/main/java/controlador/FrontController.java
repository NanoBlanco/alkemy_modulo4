package controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controlador.command.ActualizarContactoCommand;
import controlador.command.Command;
import controlador.command.CommandFactory;
import controlador.command.EditarContactoCommand;
import controlador.command.EliminarContactoCommand;
import controlador.command.GuardarContactoCommand;
import controlador.command.ListarContactoCommand;
import controlador.command.NuevoContactoCommand;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */

@WebServlet(name = "app", urlPatterns = { "/app" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//private Map<String, Command> commands = new HashMap<>();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	public void init() throws ServletException {
		commands.put("listar", new ListarContactoCommand());
		commands.put("nuevo", new NuevoContactoCommand());
		commands.put("guardar", new GuardarContactoCommand());
		commands.put("eliminar", new EliminarContactoCommand());
		commands.put("editar",new EditarContactoCommand());
		commands.put("actualizar", new ActualizarContactoCommand());
	}
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		procesar(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		procesar(req, res);
	}

	
	private void procesar(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		Command command = CommandFactory.getCommand(action);
		
		//if(action==null) action = "listar";		
		//Command command = commands.get(action);
		
		try {
			String vista = command.execute(req, res);
			if(vista != null) req.getRequestDispatcher(vista).forward(req, res);
		}catch(Exception e) {
			throw new ServletException(e);
		}
	} 
	 
	
}
