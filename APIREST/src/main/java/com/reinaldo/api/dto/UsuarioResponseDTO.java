package com.reinaldo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Datos de salida de usuario")
public record UsuarioResponseDTO(
		Long id,
		String username,
		String role,
		String email
		) {

}
