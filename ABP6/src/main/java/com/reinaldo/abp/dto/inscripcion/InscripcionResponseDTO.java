package com.reinaldo.abp.dto.inscripcion;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record InscripcionResponseDTO(
		Long id,
		Long estudianteId,
		Long cursoId,
		LocalDate fecha_inscripcion
		) {

}
