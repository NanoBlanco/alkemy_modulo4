package com.reinaldo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.TareaRequestDTO;
import com.reinaldo.api.dto.TareaResponseDTO;
import com.reinaldo.api.service.TareaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
@Validated
public class TareaController {

	private final TareaService service;
	
	public TareaController(TareaService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<TareaResponseDTO> crear(@Valid @RequestBody TareaRequestDTO dto) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.crear(dto));
	}
	
	@GetMapping
	public List<TareaResponseDTO> listar() {
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public TareaResponseDTO buscar(@PathVariable Long id) {
		return service.buscarporId(id);
	}
	
	@PutMapping("/{id}")
	public TareaResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody TareaRequestDTO dto) {
		return service.actualizar(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
