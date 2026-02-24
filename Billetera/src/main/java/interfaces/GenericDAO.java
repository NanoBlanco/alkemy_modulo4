package interfaces;

import java.util.List;

public interface GenericDAO<T, ID> {
	T buscarPorId(ID id);
	List<T> listar();
	void guardar(T entidad);
	void actualizar(T entidad);
	void eliminar(ID id);
}
