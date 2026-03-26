package com.reinaldo.abp.dto.evaluacion;

import lombok.Builder;

@Builder
public record EvaluacionResponseDTO(
		Long id,
		String nombre,
		Double puntuacion,
		Long cursoId
		) {

}
