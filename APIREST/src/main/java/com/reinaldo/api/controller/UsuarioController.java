package com.reinaldo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.UsuarioRequestDTO;
import com.reinaldo.api.dto.UsuarioResponseDTO;
import com.reinaldo.api.entity.Usuario;
import com.reinaldo.api.mapper.UsuarioMapper;
import com.reinaldo.api.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios")
public class UsuarioController {

	private final UsuarioService servicio;
	private final UsuarioMapper mapper;
	private final PasswordEncoder encoder;
	
	@Operation(summary = "Lista todos los Usuarios")
	@GetMapping
	public List<Usuario> listar() {
		return servicio.listar();
	}

	@Operation(summary = "Lista un Usuario por Id")
	@GetMapping("/{id}")
	public Usuario obtener(@PathVariable Long id) {
		return servicio.obtener(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Crea un Usuario")
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
		Usuario u = Usuario.builder()
				.username(dto.username())
				.password(encoder.encode(dto.password()))
				.role(dto.role())
				.email(dto.email())
				.build();
		Usuario guardado = servicio.crear(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToResponse(guardado));
	}
	
	@Operation(summary = "Actualiza un Usuario")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
		Usuario u = Usuario.builder()
				.id(id)
				.username(dto.username())
				.password(encoder.encode(dto.password()))
				.role(dto.role())
				.email(dto.email())
				.build();
		Usuario modificado = servicio.crear(u);
		return ResponseEntity.ok().body(mapper.mapToResponse(modificado));
	}
	
	@Operation(summary = "Elimina un Usuario por Id")
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		Usuario u = servicio.obtener(id);
		servicio.eliminar(u);
	}
	
}
