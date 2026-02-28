package controlador.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

	private static final Map<String, Command> commands = new HashMap<>();
	
	static {
		commands.put("nuevoUsuario", new NuevoUsuarioCommand());
		commands.put("guardarUsuario", new GuardarUsuarioCommand());
		commands.put("listarUsuarios", new ListarUsuarioCommand());
		commands.put("editarUsuario", new EditarUsuarioCommand());
		commands.put("actualizarUsuario", new ActualizarUsuarioCommand());
		commands.put("eliminarUsuario", new EliminarUsuarioCommand());
		commands.put("login", new LoginCommand());
		commands.put("validarLogin", new ValidarLoginCommand());
		commands.put("logout", new LogoutCommand());
	}
	
	public static Command getCommand(String action) {
		
		if(action == null) return new ListarUsuarioCommand();
		
		Command cmd = commands.get(action);
		
		if(cmd == null) throw new IllegalArgumentException("Accion no válida: "+action);
		
		return cmd;
	}
}
