package com.reinaldo.inv.service;

import java.util.List;
import java.util.Optional;

import com.reinaldo.inv.model.Categoria;
import com.reinaldo.inv.model.Producto;

public interface ProductoService {
	List<Producto> listar();
	Optional<Producto> obtener(Integer id);
	void guardar(Producto p);
	void eliminar(Integer id);
	List<Categoria> listarCategorias();
}
