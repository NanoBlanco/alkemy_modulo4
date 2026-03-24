package com.reinaldo.api.dto;

import java.util.List;

public record AuthResponseDTO(
		String token,
		String username,
		List<String> role
		) {

}
