package com.reinaldo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.api.dto.CategoriaDTO;
import com.reinaldo.api.entity.Categoria;
import com.reinaldo.api.exception.ResourceNotFoundException;
import com.reinaldo.api.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository repo;
	
	@Override
	public Categoria crear(CategoriaDTO dto) {
		Categoria cat = Categoria.builder()
				.nombre(dto.getNombre())
				.build();
		return repo.save(cat);
	}

	@Override
	public Categoria obtenerPorId(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
	}

	@Override
	public List<Categoria> listar() {
		return repo.findAll();
	}

	@Override
	public Categoria actualizar(Long id, CategoriaDTO dto) {
		Categoria cat = obtenerPorId(id);
		cat.setNombre(dto.getNombre());
		return repo.save(cat);
	}

	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
		
	}

}
