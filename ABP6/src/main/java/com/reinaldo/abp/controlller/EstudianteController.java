package com.reinaldo.abp.controlller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.abp.dto.estudiante.EstudianteRequestDTO;
import com.reinaldo.abp.dto.estudiante.EstudianteResponseDTO;
import com.reinaldo.abp.mapper.EstudianteMapper;
import com.reinaldo.abp.service.EstudianteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

	private final EstudianteService service;
	
	@GetMapping
	public List<EstudianteResponseDTO> listar(){
		return service.listar()
				.stream()
				.map(EstudianteMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public EstudianteResponseDTO obtenerPorId(@PathVariable Long id) {
		return EstudianteMapper.toDTO(service.obtenerPorId(id));
	}
	
	
	@PostMapping
	public EstudianteResponseDTO grabar(@RequestBody EstudianteRequestDTO dto) {
		var stud = EstudianteMapper.toEntity(dto);
		var grabado = service.grabar(stud);
		return EstudianteMapper.toDTO(grabado);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		service.eliminar(id);
	}
}
