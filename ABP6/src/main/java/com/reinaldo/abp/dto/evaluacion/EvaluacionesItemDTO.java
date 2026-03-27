package com.reinaldo.abp.dto.evaluacion;

import lombok.Builder;

@Builder
public record EvaluacionesItemDTO(
		Long evaluacionId,
		String evaluacionNombre,
		Double puntuacion
		) {

}
