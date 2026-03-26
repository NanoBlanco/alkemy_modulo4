package com.reinaldo.abp.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.reinaldo.abp.dto.estudiante.EstudianteRequestDTO;
import com.reinaldo.abp.dto.estudiante.EstudianteResponseDTO;
import com.reinaldo.abp.entity.Estudiante;

@Component
public class EstudianteMapper {

	public static Estudiante toEntity(EstudianteRequestDTO dto) {
		return Estudiante.builder()
				.nombre(dto.nombre())
				.correo(dto.correo())
				.build();
	}
	
	public static EstudianteResponseDTO toDTO(Estudiante entity) {
		return EstudianteResponseDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.correo(entity.getCorreo())
				.cursos(entity.getCursos() != null 
				? entity.getCursos()
						.stream()
						.map(CursoMapper::toDTO)
						.collect(Collectors.toList()): null)
				.build();
	}
}
