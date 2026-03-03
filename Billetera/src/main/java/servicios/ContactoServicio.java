package servicios;

import java.util.ArrayList;
import java.util.List;

import dao.ContactoDAO;
import modelos.Contacto;


public class ContactoServicio {

	private final ContactoDAO dao;
		
	public ContactoServicio(ContactoDAO dao) {this.dao = dao;}	
	
	public void agregarContacto(Contacto c) { dao.guardar(c);}
	
	public List<Contacto> listarContactos() { return dao.listar();	}
	
	public Contacto buscarContactoPorId(int id) { return dao.buscarPorId(id); }
	
	public void actualizarContacto(Contacto c) { dao.actualizar(c); }
	
	public void eliminarContacto(int id) { dao.eliminar(id); } 
	
	
	public List<String> validarCredenciales(String nombre, String correo) {
		
		List<String> errores = new ArrayList<>();
		
		if(nombre.length() < 5 || nombre.length() > 50) {
			errores.add("El nombre debe tener entre 5 y 50 caracteres");
		}
		
		if(!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+){0,9}$")) {
			errores.add("El nombre solo puede contener letras");
		}
		
		if(correo.length() < 10 || correo.length() > 150) {
			errores.add("El usuario debe tener entre 5 y 20 caracteres");
		}
		
		if(!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			errores.add("El usuario solo puede contener letras, numeros y guión bajo");
		}
		
		return errores;
	}
}
