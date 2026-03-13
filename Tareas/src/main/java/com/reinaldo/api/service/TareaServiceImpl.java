package com.reinaldo.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reinaldo.api.dto.TareaRequestDTO;
import com.reinaldo.api.dto.TareaResponseDTO;
import com.reinaldo.api.entity.Tarea;
import com.reinaldo.api.exception.ResourceNotFoundException;
import com.reinaldo.api.mapper.TareaMapper;
import com.reinaldo.api.repository.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {
	
	private final TareaRepository repo;
	
	public TareaServiceImpl(TareaRepository repo) {
		this.repo = repo;
	}

	@Override
	public TareaResponseDTO crear(TareaRequestDTO dto) {

		Tarea t = TareaMapper.toEntity(dto);
		
		Tarea guardada = repo.save(t);
		
		return TareaMapper.toDTO(guardada);
	}

	@Override
	public List<TareaResponseDTO> listar() {
		
		return repo.findAll()
				.stream()
				.map(TareaMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public TareaResponseDTO buscarporId(Long id) {
		Tarea t = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada"));
		
		return TareaMapper.toDTO(t);
	}

	@Override
	public TareaResponseDTO actualizar(Long id, TareaRequestDTO dto) {
		Tarea t = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
		t.setTitulo(dto.getTitulo());
		t.setDescripcion(dto.getDescripcion());
		t.setCompletada(dto.getCompletada());
		
		return TareaMapper.toDTO(repo.save(t));
	}

	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
	}

}
