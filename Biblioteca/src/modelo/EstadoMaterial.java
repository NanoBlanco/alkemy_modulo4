package modelo;

public enum EstadoMaterial {
	DISPONIBLE("Disponible para préstamo"), 
	PRESTADO("Actualmente préstado"), 
	EN_REPARACION("En mantenimiento");
	
	private final String descripcion;
	
	EstadoMaterial(String desc) {
		this.descripcion = desc;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}
