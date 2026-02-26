package modelo;

import java.util.List;

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
}
