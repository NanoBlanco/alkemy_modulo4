package modelo;

import enums.Prioridad;

public class TareaNormal extends Tarea {

	public TareaNormal(int tarea_id, String descripcion, Prioridad prioridad) {
		super(tarea_id, descripcion, prioridad);
	}

	@Override
	public void ejecutar() {
		System.out.println("[Normal] Ejecutando tarea "+getDescripcion());
	}

	@Override
	public String obtenerResumen() {
		return String.format("[NORMAL] %s | Prioridad: %s | Estado: %s", getDescripcion(), getPrioridad(), getEstado());
	}

}
