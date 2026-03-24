package modelo;

import enums.Prioridad;

public class TareaUrgente extends Tarea {

	private String deadline;
	
	public TareaUrgente(int tarea_id, String descripcion, Prioridad prioridad, String deadline) {
		super(tarea_id, descripcion, prioridad);
		this.deadline = deadline;
	}

	
	public String getDeadline() { return deadline; }


	public void setDeadline(String deadline) { this.deadline = deadline; }

	@Override
	public void ejecutar() {
		System.out.println("[Urgente] Ejecutando tarea "+getDescripcion()+" | Deadline: "+deadline);
	}

	@Override
	public String obtenerResumen() {
		return String.format("[Urgente] %s | DeadLine: %s | Estado: %s", getDescripcion(), deadline, getEstado());
	}
}
