package com.reinaldo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.CategoriaDTO;
import com.reinaldo.api.entity.Categoria;
import com.reinaldo.api.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias")
public class CategoriaController {

	private final CategoriaService service;
	
	@Operation(summary = "Crear categoria")
	@PostMapping
	public ResponseEntity<Categoria> crear(@Valid @RequestBody CategoriaDTO dto){
		
		Categoria cat = service.crear(dto);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(cat);
	}
	
	@Operation(summary = "Listar categorias")
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		return ResponseEntity.ok(service.listar());
	}
	
	@Operation(summary = "Obtener categoria por Id")
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obtener(@PathVariable Long id) {
		return ResponseEntity.ok(service.obtenerPorId(id));
	}
	
	@Operation(summary = "Actualizar categoria")
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
		return ResponseEntity.ok(service.actualizar(id, dto));
	}
	
	@Operation(summary = "Eliminar una categoria")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
