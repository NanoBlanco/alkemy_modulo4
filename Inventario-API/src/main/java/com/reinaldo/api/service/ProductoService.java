package com.reinaldo.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reinaldo.api.dto.ProductoRequestDTO;
import com.reinaldo.api.entity.Producto;

public interface ProductoService {
	
	Producto crear(ProductoRequestDTO dto);
	
	Producto obtenerPorId(Long id);
	
	Page<Producto> listar(
			String nombre, 
			Long categoria, 
			Integer minPrecio, 
			Integer maxPrecio, 
			Pageable pageable);

	Producto actualizar(Long id, ProductoRequestDTO dto);
	
	
	
	void eliminar(Long id);
}
