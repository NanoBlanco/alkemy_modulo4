package modelos;

public class Libro {

	private int id;
	private String titulo;
	private boolean prestado;
	
	public Libro(int id, String titulo) {
		this.id = id;
		this.titulo = titulo;
		this.prestado = false;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean estaPrestado() {
		return prestado;
	}
	
	public void prestar() {
		prestado = true;
	}
	
	public void devolver() {
		prestado = false;
	}
	
	public void mostrarInfo() {
		System.out.println("\nId "+id+" - "+titulo+" - "+ (prestado ? "Prestado" : "Disponible"));
	}
}
