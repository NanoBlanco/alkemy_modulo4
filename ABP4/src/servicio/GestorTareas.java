package servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import enums.Prioridad;
import enums.TipoUsuario;
import modelo.Estudiante;
import modelo.Tarea;
import modelo.TareaNormal;
import modelo.TareaUrgente;
import modelo.Trabajador;
import modelo.Usuario;

public class GestorTareas {

	private final List<Usuario> usuarios;
	private int contadorUsuarios;
	private int contadorTareas;
	
	public GestorTareas() {
		this.usuarios = new ArrayList<>();
		this.contadorUsuarios = 0;
		this.contadorTareas = 0;
	}
	
	// Gestion de usuario
	public Usuario creaUsuario(String nombre, TipoUsuario tipo) {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre no puede estar vacio");
		}
		int nuevoId = ++contadorUsuarios;
		Usuario usuario = switch (tipo) {
		case ESTUDIANTE -> 	new Estudiante(nuevoId, nombre);
		case TRABAJADOR -> new Trabajador(nuevoId, nombre);
		};
		usuarios.add(usuario);
		return usuario;
	}
	
	public List<Usuario> listarUsuario() {
		return usuarios;
	}
	
	public List<Usuario> filtrarUsuarioPorTipo(TipoUsuario tipo) {
		return usuarios.stream()
				.filter(u -> u.getTipo() == tipo)
				.collect(Collectors.toList());
	}
	
	public Optional<Usuario> buscarUsuario(int idUsuario) {
		return usuarios.stream().filter(u -> u.getUsuario_id() == idUsuario).findFirst();
	}
	
	// Gestion de Tareas
	public Tarea agragarTareaNormal(int idUsuario, String descripcion, Prioridad prioridad) {
		Usuario u = buscarUsuario(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		Tarea t = new TareaNormal(++contadorTareas, descripcion, prioridad);
		
		u.agregarTarea(t);
		return t;
	}
	
	public Tarea agragarTareaUrgente(int idUsuario, String descripcion, Prioridad prioridad, String deadline) {
		Usuario u = buscarUsuario(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		Tarea t = new TareaUrgente(++contadorTareas, descripcion, prioridad, deadline);
		
		u.agregarTarea(t);
		return t;
	}
	
	// Las tareas de listar es igual a las del usuario
	
	public boolean marcarTareaComoCompletada(int idUsuario, int idTarea) {
		Usuario u = buscarUsuario(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		Tarea t = u.buscarTarea(idTarea);
		t.marcarComoCompletada();
		return true;
	}
	
	public boolean eliminarTarea(int idUsuario, int idTarea) {
		Usuario u = buscarUsuario(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		u.eliminarTarea(idTarea);
		return true;
	}
}
