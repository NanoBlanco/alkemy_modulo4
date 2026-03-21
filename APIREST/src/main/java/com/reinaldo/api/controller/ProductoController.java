package com.reinaldo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.ProductoRequestDTO;
import com.reinaldo.api.dto.ProductoResponseDTO;
import com.reinaldo.api.entity.Producto;
import com.reinaldo.api.mapper.ProductoMapper;
import com.reinaldo.api.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos")
public class ProductoController {

	private final ProductoService servicio;
	private final ProductoMapper mapper;
	
	@PreAuthorize("hasRole('USER')")
	@Operation(summary = "Lista todos los productos")
	@GetMapping
	public List<Producto> listar() {
		return servicio.listar();
	}

	@Operation(summary = "Lista un productos por Id")
	@GetMapping("/{id}")
	public Producto obtener(@PathVariable Long id) {
		return servicio.obtener(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Crea un producto")
	@PostMapping
	public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {
		Producto p = Producto.builder().nombre(dto.nombre()).precio(dto.precio()).build();
		Producto guardado = servicio.crear(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToResponse(guardado));
	}
	
	@Operation(summary = "Actualiza un producto")
	@PutMapping("/{id}")
	public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
		Producto p = Producto.builder().id(id).nombre(dto.nombre()).precio(dto.precio()).build();
		Producto modificado = servicio.crear(p);
		return ResponseEntity.ok().body(mapper.mapToResponse(modificado));
	}
	
	@Operation(summary = "Elimina un producto por Id")
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		Producto p = servicio.obtener(id);
		servicio.eliminar(p);
	}
}
