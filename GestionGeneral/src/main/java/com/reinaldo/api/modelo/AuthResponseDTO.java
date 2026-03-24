package com.reinaldo.api.modelo;

import java.util.List;

public record AuthResponseDTO(
		String token,
		String username,
		List<String> role
		) {

}