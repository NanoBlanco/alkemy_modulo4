package modelos;

public class Usuario extends Persona {

	public Usuario(int id, String nombre) {
		super(id, nombre);
	
	}

	public int getId() {
		return id;
	}
	
	@Override
	public void mostrarInfo() {
		System.out.println("\nId: "+id+"Usuario: "+nombre);

	}

}
