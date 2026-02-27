package controlador.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

	private static final Map<String, Command> commands = new HashMap<>();
	
	static {
		commands.put("listar", new ListarContactoCommand());
		commands.put("nuevo", new NuevoContactoCommand());
		commands.put("guardar", new GuardarContactoCommand());
		commands.put("eliminar", new EliminarContactoCommand());
		commands.put("editar",new EditarContactoCommand());
		commands.put("actualizar", new ActualizarContactoCommand());
	}
	
	public static Command getCommand(String action) {
		
		if(action == null) return new ListarContactoCommand();
		
		Command cmd = commands.get(action);
		
		if(cmd == null) throw new IllegalArgumentException("Accion no válida: "+action);
		
		return cmd;
	}
}
