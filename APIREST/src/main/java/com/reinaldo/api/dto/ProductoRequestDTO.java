package com.reinaldo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos para validar entrada")
public record ProductoRequestDTO(
		@NotBlank
		String nombre,

		@NotNull
		@Positive
		Integer precio,
		
		Integer stock
		){ }
