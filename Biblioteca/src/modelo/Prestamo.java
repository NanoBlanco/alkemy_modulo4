package modelo;

public class Prestamo {
	
	private Usuario usuario;
	private Libro libro;
	
	public Prestamo(Usuario usuario, Libro libro) {
		this.usuario = usuario;
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public void mostrar() {
		System.out.println("Libro: ");
		libro.mostrarLibro();
		System.out.println("Usuario");
		usuario.mostrar();
		System.out.println("-".repeat(30));
	}
}
