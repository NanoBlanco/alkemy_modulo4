package com.reinaldo.abp.dto.estudiante;

import java.util.List;

import com.reinaldo.abp.dto.curso.CursoResponseDTO;

import lombok.Builder;

@Builder
public record EstudianteResponseDTO(
		Long id,
		String nombre,
		String correo,
		List<CursoResponseDTO> cursos
		) {

}
