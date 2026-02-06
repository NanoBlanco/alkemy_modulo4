package modelo;

public class Estudiante extends Usuario {

	public Estudiante(String id, String nombre) {
		super(id, nombre);
	}

	@Override
	public int maxPrestamos() {
		return 2;
	}

}
