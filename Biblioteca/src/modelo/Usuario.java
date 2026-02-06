package modelo;

public abstract class Usuario {
	
	protected String id;
	protected String nombre;
	protected int librosPrestados;
	
	public Usuario(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public abstract int maxPrestamos();
	
	public boolean puedePedir() {
		return librosPrestados < maxPrestamos();
	}
	
	public void prestarLibro() {
		librosPrestados++;
	}
	
	public void devolverLibro() {
		librosPrestados--;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void mostrar() {
		System.out.println("Nombre: "+nombre+" ("+id+")");
	}
}
