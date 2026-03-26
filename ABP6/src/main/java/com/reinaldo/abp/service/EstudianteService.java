package com.reinaldo.abp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reinaldo.abp.entity.Estudiante;
import com.reinaldo.abp.exception.RecursoNoEncontradoException;
import com.reinaldo.abp.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteService {

	private final EstudianteRepository repo;
	
	public List<Estudiante> listar() {
		return repo.findAll();
	}
	
	public Estudiante obtenerPorId(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado"));
	}
	
	public Estudiante grabar(Estudiante e) {
		return repo.save(e);
	}
	
	public void eliminar(Long id) {
		Estudiante e = obtenerPorId(id);
		repo.delete(e);
	}
}
