package com.reinaldo.abp.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.reinaldo.abp.dto.curso.CursoRequestDTO;
import com.reinaldo.abp.dto.curso.CursoResponseDTO;
import com.reinaldo.abp.entity.Curso;

@Component
public class CursoMapper {

	public static Curso toEntity(CursoRequestDTO dto) {
		return Curso.builder()
				.nombre(dto.nombre())
				.build();
	}
	
	public static CursoResponseDTO toDTO(Curso entity) {
		return CursoResponseDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.estudianteId(entity.getEstudiante() != null ? entity.getEstudiante().getId() : null)
				.evaluaciones(entity.getEvaluaciones() != null 
				? entity.getEvaluaciones()
						.stream()
						.map(EvaluacionMapper::toDTO)
						.collect(Collectors.toList()) 
						: null)
				.build();
	}
}
