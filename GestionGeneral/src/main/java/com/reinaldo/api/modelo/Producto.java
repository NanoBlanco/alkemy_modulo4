package com.reinaldo.api.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Producto {

	private Long id;
	private String nombre;
	private Integer precio;
	private Integer stock;
}