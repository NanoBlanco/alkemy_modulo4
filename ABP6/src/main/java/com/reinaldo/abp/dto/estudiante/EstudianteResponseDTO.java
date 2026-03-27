package com.reinaldo.abp.dto.estudiante;

import lombok.Builder;

@Builder
public record EstudianteResponseDTO(
		Long id,
		String nombre,
		String correo
		) {

}
