package com.reinaldo.api.mapper;

import org.springframework.stereotype.Component;

import com.reinaldo.api.dto.ProductoResponseDTO;
import com.reinaldo.api.entity.Producto;

@Component
public class ProductoMapper {

	public ProductoResponseDTO toDTO(Producto p) {
		return ProductoResponseDTO.builder()
				.id(p.getId())
				.nombre(p.getNombre())
				.precio(p.getPrecio())
				.stock(p.getStock())
				.categoria(p.getCategoria().getNombre())
				.build();
	}
}
