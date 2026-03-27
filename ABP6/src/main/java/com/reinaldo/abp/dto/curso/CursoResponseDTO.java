package com.reinaldo.abp.dto.curso;

import lombok.Builder;

@Builder
public record CursoResponseDTO(
		Long id,
		String nombre
		) {

}
