package modelos;

public abstract class Persona {
	
	protected int id;
	protected String nombre;
	
	public Persona(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public abstract void mostrarInfo();

}
