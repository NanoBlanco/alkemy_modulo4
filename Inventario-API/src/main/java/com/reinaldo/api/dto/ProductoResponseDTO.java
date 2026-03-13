package com.reinaldo.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoResponseDTO {

	private Long id;
	private String nombre;
	private Integer precio;
	private Integer stock;
	private String categoria;
}
