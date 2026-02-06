package modelo;

public class Profesor extends Usuario {

	public Profesor(String id, String nombre) {
		super(id, nombre);
	}

	@Override
	public int maxPrestamos() {
		return 5;
	}

}
