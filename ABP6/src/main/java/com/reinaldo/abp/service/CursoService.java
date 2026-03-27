package com.reinaldo.abp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reinaldo.abp.entity.Curso;
import com.reinaldo.abp.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

	private final CursoRepository repo;
	
	public Curso grabar(Curso curso) {
		return repo.save(curso);
	}
	
	public List<Curso> listar(){
		return repo.findAll();
	}
}
