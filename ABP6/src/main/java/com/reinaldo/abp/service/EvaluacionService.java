package com.reinaldo.abp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reinaldo.abp.entity.Curso;
import com.reinaldo.abp.entity.Evaluacion;
import com.reinaldo.abp.exception.RecursoNoEncontradoException;
import com.reinaldo.abp.repository.CursoRepository;
import com.reinaldo.abp.repository.EvaluacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluacionService {

	private final EvaluacionRepository repo;
	private final CursoRepository repoCur;
	
	public Evaluacion grabar(Long cursoId, Evaluacion eva) {
		Curso c = repoCur.findById(cursoId)
				.orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado"));
		eva.setCurso(c);
		return repo.save(eva);
	}
	
	public List<Evaluacion> listar(){
		return repo.findAll();
	}
}
