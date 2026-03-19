package com.reinaldo.api.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.ProductoRequestDTO;
import com.reinaldo.api.dto.ProductoResponseDTO;
import com.reinaldo.api.entity.Producto;
import com.reinaldo.api.mapper.ProductoMapper;
import com.reinaldo.api.service.ProductoServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name="Productos")
public class ProductoController {

	private final ProductoServiceImpl service;
	private final ProductoMapper mapper;
	
	@Operation(summary="Crear Producto")
	@ApiResponse(responseCode="201", description="Producto creado")
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody ProductoRequestDTO dto) {
		Producto prod = service.crear(dto);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(mapper.toDTO(prod));
	}
	
	@Operation(summary="Obtener producto por Id")
	@GetMapping("/{id}")
	public ResponseEntity<ProductoResponseDTO> obtener(@PathVariable Long id) {

		Producto p = service.obtenerPorId(id);
		
		return ResponseEntity.ok(mapper.toDTO(p));
	}
	
	@Operation(summary="Listar productos con filtros")
	@GetMapping
	public ResponseEntity<Page<ProductoResponseDTO>> buscar(
			@RequestParam(required=false) String nombre,
			@RequestParam(required=false) Long categoria,
			@RequestParam(required=false) Integer minPrecio,
			@RequestParam(required=false) Integer maxPrecio,
			@PageableDefault(sort = "nombre")
			@ParameterObject Pageable pageable){
		
		
		Page<Producto> productos = service.listar(nombre, categoria, minPrecio, maxPrecio, pageable);
		
		Page<ProductoResponseDTO> dto = productos.map(mapper::toDTO);
		
		return ResponseEntity.ok(dto);
	}
	
	@Operation(summary = "Actualizar producto")
	@PutMapping("/{id}")
	public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {

		Producto p = service.actualizar(id, dto);
		
		return ResponseEntity.ok(mapper.toDTO(p));
	}
	
	@Operation(summary = "Eliminar producto")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {

		service.eliminar(id);
		
		return ResponseEntity.noContent().build();
	}
}
