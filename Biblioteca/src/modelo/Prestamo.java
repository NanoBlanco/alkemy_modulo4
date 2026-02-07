package modelo;

public class Prestamo {
	
	private Usuario usuario;
	private MaterialBiblioteca libro;
	
	public Prestamo(Usuario usuario, MaterialBiblioteca libro) {
		this.usuario = usuario;
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public MaterialBiblioteca getLibro() {
		return libro;
	}

	public void mostrar() {
		System.out.println("Libro: ");
		libro.mostrarDetalles();
		System.out.println("Usuario");
		usuario.mostrar();
		System.out.println("-".repeat(30));
	}
}
