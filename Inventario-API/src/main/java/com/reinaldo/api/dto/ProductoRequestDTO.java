package com.reinaldo.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductoRequestDTO {
	@NotBlank
	private String nombre;
	@NotNull
	private Integer precio;
	@NotNull
	private Integer stock;
	@NotNull
	private Long categoriaId;
}
