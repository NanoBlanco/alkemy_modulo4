package modelo;

import java.util.ArrayList;
import java.util.List;

import enums.TipoUsuario;

public abstract class Usuario {
	
	private final int usuario_id;
	private String nombre;
	private final TipoUsuario tipo;
	private final List<Tarea> tareas;
	
	protected Usuario(int id, String nombre, TipoUsuario tipo) {
		this.usuario_id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.tareas = new ArrayList<>();
	}

	public int getUsuario_id() { return usuario_id; }

	public String getNombre() { return nombre; }

	public void setNombre(String nombre) { this.nombre = nombre; }

	public TipoUsuario getTipo() { return tipo; }

	public List<Tarea> getTareas() { return tareas; }
	
	public int totalTareas() { return tareas.size(); }
	
	public void agregarTarea(Tarea t) {
		if (t != null) tareas.add(t);
	}
	
	public boolean eliminarTarea(int idTarea) {
		return tareas.removeIf(t -> t.getTarea_id() == idTarea);
	}
	
	public Tarea buscarTarea(int idTarea) {
		return tareas.stream()
				.filter(t -> t.getTarea_id() == idTarea)
				.findFirst()
				.orElse(null);
	}
	
	public abstract String obtenerRol();
}
