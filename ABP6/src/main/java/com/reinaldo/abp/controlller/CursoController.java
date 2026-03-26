package com.reinaldo.abp.controlller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.abp.dto.curso.CursoRequestDTO;
import com.reinaldo.abp.dto.curso.CursoResponseDTO;
import com.reinaldo.abp.mapper.CursoMapper;
import com.reinaldo.abp.service.CursoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

	private final CursoService service;
	
	@PostMapping("/estudiante/{estudianteId}")
	public CursoResponseDTO grabar(
			@PathVariable Long estudianteId, 
			@RequestBody CursoRequestDTO dto) {
		var curso = CursoMapper.toEntity(dto);
		var grabado = service.grabar(estudianteId, curso);
		return CursoMapper.toDTO(grabado);
	}
	
	@GetMapping
	public List<CursoResponseDTO> listar(){
		return service.listar().stream().map(CursoMapper::toDTO).collect(Collectors.toList()) ;
	}
	
}
