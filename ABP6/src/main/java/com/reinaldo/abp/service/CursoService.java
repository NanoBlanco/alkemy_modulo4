package com.reinaldo.abp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reinaldo.abp.entity.Curso;
import com.reinaldo.abp.entity.Estudiante;
import com.reinaldo.abp.exception.RecursoNoEncontradoException;
import com.reinaldo.abp.repository.CursoRepository;
import com.reinaldo.abp.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

	private final CursoRepository repo;
	private final EstudianteRepository repoSt;
	
	public Curso grabar(Long estudianteId, Curso curso) {
		
		Estudiante estudiante = repoSt.findById(estudianteId).orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado"));
		curso.setEstudiante(estudiante);
		
		return repo.save(curso);
	}
	
	public List<Curso> listar(){
		return repo.findAll();
	}
}
