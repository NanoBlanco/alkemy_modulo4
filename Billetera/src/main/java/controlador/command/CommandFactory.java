package controlador.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

	private static final Map<String, Command> commands = new HashMap<>();
	
	static {
		commands.put("login", new LoginCommand());
		commands.put("validarLogin", new ValidarLoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("nuevoRegistro", new NuevoRegistroCommand());
		commands.put("guardarRegistro", new GuardarRegistroCommand());
		commands.put("editarRegistro", new EditarRegistroCommand());
		commands.put("actualizarRegistro", new ActualizarRegistroCommand());
		commands.put("nuevoUsuario", new NuevoUsuarioCommand());
		commands.put("guardarUsuario", new GuardarUsuarioCommand());
		commands.put("listarUsuarios", new ListarUsuarioCommand());
		commands.put("editarUsuario", new EditarUsuarioCommand());
		commands.put("actualizarUsuario", new ActualizarUsuarioCommand());
		commands.put("eliminarUsuario", new EliminarUsuarioCommand());
		commands.put("contactos", new ContactosCommand());
		commands.put("guardarContacto", new GuardarContactoCommand());
		commands.put("editarContacto", new EditarContactoCommand());
		commands.put("actualizarContacto", new ActualizarContactoCommand());
		commands.put("eliminarContacto", new EliminarContactoCommand());
		commands.put("deposito", new DepositoCommand());
		commands.put("depositoNuevo", new DepositoNuevoCommand());
	}
	
	public static Command getCommand(String action) {
		
		if(action == null) return new ListarUsuarioCommand();
		
		Command cmd = commands.get(action);
		
		if(cmd == null) throw new IllegalArgumentException("Accion no válida: "+action);
		
		return cmd;
	}
}
