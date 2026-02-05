package modelo;

public class Libro {

	private String isbn;
	private String titulo;
	private String autor;
	private boolean prestado;
	
	public Libro(String isbn, String titulo, String autor) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.prestado = false;
	}

	public String getIsbn() {
		return isbn;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void prestar() {
		if(!prestado) {
			prestado = true;
		}
	}
	
	public void devolver() {
		if(prestado) {
			prestado = false;
		}
	}
	
	public void mostrarLibro() {
		System.out.println("\nISBN: "+isbn+"\nTitulo: "+titulo+"\nAutor: "+autor+(!prestado ? " Disponible":" Prestado"));
	}
}
