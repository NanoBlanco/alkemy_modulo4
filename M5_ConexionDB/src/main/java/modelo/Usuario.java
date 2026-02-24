package modelo;

public class Usuario {
	
	private int id_usuario;
	private String nombre;
	private String correo;
	private String clave;
	
	public Usuario() {}
	
	public Usuario(int id_usuario, String nombre, String correo, String clave) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	

}
