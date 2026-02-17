package modelos;

public class Prestamo {
	
	private Libro libro;
	private Usuario usuario;
	
	public Prestamo(Libro libro, Usuario usuario) {
		this.libro = libro;
		this.usuario = usuario;
	}
	
	public Libro getLibro() {
		return libro;
	}

	public void mostrarInfo() {
		System.out.println("Libro prestado a: ");
		usuario.mostrarInfo();
	}
}
