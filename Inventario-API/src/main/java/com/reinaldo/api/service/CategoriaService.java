package com.reinaldo.api.service;

import java.util.List;

import com.reinaldo.api.dto.CategoriaDTO;
import com.reinaldo.api.entity.Categoria;

public interface CategoriaService {
	
	Categoria crear(CategoriaDTO dto);
	
	Categoria obtenerPorId(Long id);
	
	List<Categoria> listar();
	
	Categoria actualizar(Long id, CategoriaDTO dto);
	
	void eliminar(Long id);

}
