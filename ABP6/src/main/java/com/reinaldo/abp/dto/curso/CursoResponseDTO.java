package com.reinaldo.abp.dto.curso;

import java.util.List;

import com.reinaldo.abp.dto.evaluacion.EvaluacionResponseDTO;

import lombok.Builder;

@Builder
public record CursoResponseDTO(
		Long id,
		String nombre,
		Long estudianteId,
		List<EvaluacionResponseDTO> evaluaciones
		) {

}
