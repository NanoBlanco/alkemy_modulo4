package modelos;

public class Usuario {
	
	private int id;
	private String nombre;
	private Cuenta cuenta;
	
	public Usuario(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.cuenta = new Cuenta();
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void mostrarInfo() {
		System.out.println("\nId "+id+" - "+nombre);
	}
	

}
