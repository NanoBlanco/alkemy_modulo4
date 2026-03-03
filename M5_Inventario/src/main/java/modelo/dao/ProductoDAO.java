package modelo.dao;

import java.util.List;
import java.util.Optional;

import modelo.Producto;

public interface ProductoDAO {
	
	List<Producto> findAll();
	Optional<Producto> findById(int id);
	boolean insert(Producto p);
	boolean update(Producto p);
	boolean delete(int id);

}
