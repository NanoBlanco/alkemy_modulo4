package modelo;

public class Usuario {
	
	private int id;
	private String username;
	private String clave;
	private String nombre;
	private String correo;
	private String rol;
	
	public Usuario(int id, String username, String clave, String nombre, String correo, String rol) {
		this.id = id;
		this.username = username;
		this.clave = clave;
		this.nombre = nombre;
		this.correo = correo;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getClave() {
		return clave;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getRol() {
		return rol;
	}
	
	
}
