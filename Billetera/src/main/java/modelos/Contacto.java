package modelos;

public class Contacto extends Persona {
	
	public Contacto(int id, String nombre, String correo) {
		super(id, nombre, correo);
	}

	@Override
	public void mostrarInfo() {
		System.out.println("\nId: "+id + " - " + nombre + " - "+correo);
	}

	
}
