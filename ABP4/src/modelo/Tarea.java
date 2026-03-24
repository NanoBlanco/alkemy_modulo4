package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import enums.EstadoTarea;
import enums.Prioridad;
import interfaces.Acciones;

public abstract class Tarea implements Acciones{

	private final int tarea_id;
	private String descripcion;
	private Prioridad prioridad;
	private EstadoTarea estado;
	private final LocalDateTime fechaCreacion;
	
	private static final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	protected Tarea(int tarea_id, String descripcion, Prioridad prioridad) {
		super();
		this.tarea_id = tarea_id;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.estado = EstadoTarea.ACTIVA;
		this.fechaCreacion = LocalDateTime.now();
	}

	public int getTarea_id() { return tarea_id; }
	
	public String getDescripcion() { return descripcion; }

	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

	public Prioridad getPrioridad() { return prioridad; }

	public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }

	public EstadoTarea getEstado() { return estado; }

	public void marcarComoCompletada() { this.estado = EstadoTarea.COMPLETADA; }
	
	public boolean estaCompletada() { return estado == EstadoTarea.COMPLETADA; }

	public LocalDateTime getFechaCreacion() { return fechaCreacion; }
	
	
}
