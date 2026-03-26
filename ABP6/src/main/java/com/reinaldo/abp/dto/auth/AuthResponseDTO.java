package com.reinaldo.abp.dto.auth;

import java.util.List;

public record AuthResponseDTO(
		String token,
		String username,
		List<String> role
		) {

}
