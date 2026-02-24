package servicios;

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
	
	public void eliminarContatco(int id) { dao.eliminar(id); } 
}
