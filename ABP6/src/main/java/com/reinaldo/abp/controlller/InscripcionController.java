package com.reinaldo.abp.controlller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.abp.dto.inscripcion.InscripcionRequestDTO;
import com.reinaldo.abp.dto.inscripcion.InscripcionResponseDTO;
import com.reinaldo.abp.mapper.InscripcionMapper;
import com.reinaldo.abp.service.InscripcionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

	private final InscripcionService service;
	
	@PostMapping
	public InscripcionResponseDTO grabar(@RequestBody InscripcionRequestDTO dto) {
		var inscripcion = service.grabar(dto.estudianteId(), dto.cursoId());
		
		return InscripcionMapper.toDTO(inscripcion);
	}
	
	@GetMapping
	public List<InscripcionResponseDTO> listar(){
		return service.listar()
				.stream()
				.map(InscripcionMapper::toDTO)
				.toList();
	}
}
