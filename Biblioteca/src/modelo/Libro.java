package modelo;

public class Libro extends MaterialBiblioteca {

	private String isbn;
	private String autor;
	private int numeroPaginas;
	
	public Libro(String codigo, String titulo, int año, String isbn,  String autor, int paginas) {
		super(codigo, titulo, año);
		this.isbn = isbn;
		this.autor = autor;
		this.numeroPaginas = paginas;
	}

	public String getIsbn() {return isbn;}

	
	@Override
	public void mostrarDetalles() {
		System.out.println("Libro");
		System.out.println("\nCodigo: "+codigo+"\nTitulo: "+titulo+"\nAutor: "+autor+"\nISBN: "+isbn+"\nNumero de Paginas "+numeroPaginas+"\nAño de Publicacion "+añoPublicacion+"\nEstado "+estado.getDescripcion());
		
	}
}
