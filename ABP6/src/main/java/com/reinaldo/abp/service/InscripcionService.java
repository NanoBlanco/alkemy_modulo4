package com.reinaldo.abp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reinaldo.abp.entity.Inscripcion;
import com.reinaldo.abp.exception.RecursoNoEncontradoException;
import com.reinaldo.abp.repository.CursoRepository;
import com.reinaldo.abp.repository.EstudianteRepository;
import com.reinaldo.abp.repository.InscripcionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscripcionService {

	private final InscripcionRepository repoIns;
	private final EstudianteRepository repoEst;
	private final CursoRepository repoC;
	
	public Inscripcion grabar(Long estudianteId, Long cursoId) {
		var estudiante = repoEst.findById(estudianteId).orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado"));
		var curso = repoC.findById(cursoId).orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado"));
		
		var inscripcion = Inscripcion.builder()
				.estudiante(estudiante)
				.curso(curso)
				.fecha_inscripcion(LocalDate.now())
				.build();
		
		return repoIns.save(inscripcion);
	}
	
	public List<Inscripcion> listar(){
		return repoIns.findAll();
	}
}
