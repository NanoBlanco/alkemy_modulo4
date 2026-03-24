package com.reinaldo.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
		@NotBlank
		String username,
		
		@NotBlank
		String password,
		
		@NotBlank
		String role,
		
		@NotBlank
		String email
		) {

}
