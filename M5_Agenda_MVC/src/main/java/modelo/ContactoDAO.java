package modelo;

import java.util.List;

public interface ContactoDAO {
	List<ContactoDTO> listar();
	void guardar(ContactoDTO dto);
	ContactoDTO buscarPorId(int id);
	void actualizar(ContactoDTO dto);
	void eliminar(int id);
}
