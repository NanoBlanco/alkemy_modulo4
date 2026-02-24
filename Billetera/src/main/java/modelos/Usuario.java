package modelos;

import java.util.List;

public class Usuario extends Persona{
	
	private String clave;
	private String rol;
	private Billetera billetera;
	private List<Contacto> contactos;
	
	public Usuario(int id, String nombre, String correo, String clave, String rol) {
		super(id, nombre, correo);
		this.clave = clave;
		this.rol = rol;
		this.billetera = new Billetera();
	}

	public String getClave() { return clave; }

	public String getRol() { return rol; }
	
	public Billetera getBilletera() { return billetera; }

	public List<Contacto> getContactos() { return contactos; }

	public void setClave(String clave) { this.clave = clave; }

	public void setRol(String rol) { this.rol = rol; }

	public void setBilletera(Billetera billetera) { this.billetera = billetera; }
	
	public void setContacto(Contacto c) { contactos.add(c); }
	


	@Override
	public void mostrarInfo() {
		System.out.println("\nId: "+id + " - " + nombre);
		
	}
	
}
