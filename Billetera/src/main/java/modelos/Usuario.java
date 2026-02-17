package modelos;

public class Usuario {
	
	private int id;
	private String nombre;
	private Billetera billetera;
	
	public Usuario(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.billetera = new Billetera();
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}

	public Billetera getBilletera() {
		return billetera;
	}

	@Override
	public String toString() {
		return id + " - " + nombre;
	}
	
}
