package com.reinaldo.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reinaldo.api.entity.Producto;
import com.reinaldo.api.excepcion.RecursoNoEncontradoException;
import com.reinaldo.api.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoService {

	private final ProductoRepository repo;
	
	public List<Producto> listar() {
		return repo.findAll();
	}
	
	public Producto obtener(Long id) {
		return repo.findById(id)
				.orElseThrow(()-> new RecursoNoEncontradoException("Producto no Encontrado"));
	}
	
	public Producto crear(Producto p) {
		return repo.save(p);
	}
	
	public Producto actualizar(Long id, Producto p) {
		p.setId(id);
		return repo.save(p);
	}
	
	public void eliminar(Producto p) {
		repo.delete(p);
	}
	
}
