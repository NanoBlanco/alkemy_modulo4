package com.reinaldo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos para validar login")
public record AuthRequest(
		@NotBlank
		String username,
		@NotBlank
		String password
		) {

}
