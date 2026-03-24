package com.reinaldo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Datos de salida de producto")
public record ProductoResponseDTO(
		Long id,
		String nombre,
		Integer precio,
		Integer stock
		) {

}
