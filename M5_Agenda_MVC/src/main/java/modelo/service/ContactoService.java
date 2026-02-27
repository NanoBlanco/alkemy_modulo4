package modelo.service;

import java.util.List;

import modelo.ContactoDTO;
import modelo.dao.ContactoDAO;
import modelo.dao.ContactoDAOImpl;

public class ContactoService {
	
	private ContactoDAO dao = new ContactoDAOImpl();

	public List<ContactoDTO> listarContactos(){
		return dao.listar();
	}
	
	public void guardarContacto(ContactoDTO dto) {
		
		if(dto.getNombre() == null || dto.getNombre().isBlank()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}
		dao.guardar(dto);
	}
	
	public ContactoDTO obtenerContacto(int id) {
		if(id > 0) return dao.buscarPorId(id);
		return null;
	}
	
	public void actualizaContacto(ContactoDTO dto) {
		if (dto != null) dao.actualizar(dto);
	}
	
	public void eliminarContacto(int id) {
		if (id > 0) dao.eliminar(id);
	}
}
