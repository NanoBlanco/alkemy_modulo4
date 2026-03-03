package modelo.service;

import java.util.List;
import java.util.Optional;

import modelo.Producto;
import modelo.dao.ProductoDAOImpl;

public class ProductoService {
	
	private final ProductoDAOImpl dao = new ProductoDAOImpl();
	
	public List<Producto> obtenerTodos(){
		return dao.findAll();
	}

	public Optional<Producto> obtenerProducto(int id) {
		return dao.findById(id);
	}
	
	public void guardarProducto(Producto p) {
		if (p.getNombre() == null || p.getNombre().isBlank())
			throw new IllegalArgumentException("Nombre requerido");
		if (p.getPrecio() < 0)
			throw new IllegalArgumentException("Precio inválido");
		
		if (p.getId() == 0) {
			dao.insert(p);
		} else {
			dao.update(p);
		}
	}
	
	public void eliminar(int id) {
		dao.delete(id);
	}
}
