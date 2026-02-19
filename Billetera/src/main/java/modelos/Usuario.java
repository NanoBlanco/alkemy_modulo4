package modelos;

public class Usuario {
	
	private int id;
	private String username;
	private String clave;
	private String nombre;
	private String correo;
	private String rol;
	private Billetera billetera;
	
	public Usuario() { }
	
	public Usuario(int id, String username, String clave, String nombre, String correo, String rol) {
		this.id = id;
		this.username = username;
		this.clave = clave;
		this.nombre = nombre;
		this.correo = correo;
		this.rol = rol;
		this.billetera = new Billetera();
	}

	public int getId() { return id;	}

	public String getUsername() { return username; }

	public String getClave() { return clave; }

	public String getNombre() { return nombre; }

	public String getCorreo() { return correo; }

	public String getRol() { return rol; }
	
	public Billetera getBilletera() { return billetera; }

	public void setId(int id) { this.id = id; }

	public void setUsername(String username) { this.username = username; }

	public void setClave(String clave) { this.clave = clave; }

	public void setNombre(String nombre) { this.nombre = nombre; }

	public void setCorreo(String correo) { this.correo = correo; }

	public void setRol(String rol) { this.rol = rol; }

	public void setBilletera(Billetera billetera) { this.billetera = billetera; }

	@Override
	public String toString() { return id + " - " + nombre; }
	
}
