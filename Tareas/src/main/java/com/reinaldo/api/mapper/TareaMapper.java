package com.reinaldo.api.mapper;

import com.reinaldo.api.dto.TareaRequestDTO;
import com.reinaldo.api.dto.TareaResponseDTO;
import com.reinaldo.api.entity.Tarea;

public class TareaMapper {
	
	public static Tarea toEntity(TareaRequestDTO dto) {
		
		Tarea t = new Tarea();
		
		t.setTitulo(dto.getTitulo());
		t.setDescripcion(dto.getDescripcion());
		t.setCompletada(dto.getCompletada());
		
		return t;
	}

	public static TareaResponseDTO toDTO(Tarea t) {
		
		TareaResponseDTO dto = new TareaResponseDTO();
		
		dto.setId(t.getId());
		dto.setTitulo(t.getTitulo());
		dto.setDescripcion(t.getDescripcion());
		dto.setCompletada(t.getCompletada());
		
		return dto;
	}
}
