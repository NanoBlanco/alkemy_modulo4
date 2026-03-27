package com.reinaldo.abp.controlller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.abp.dto.evaluacion.EvaluacionRequestDTO;
import com.reinaldo.abp.dto.evaluacion.EvaluacionResponseDTO;
import com.reinaldo.abp.dto.evaluacion.TableroCursoDTO;
import com.reinaldo.abp.mapper.EvaluacionMapper;
import com.reinaldo.abp.service.EvaluacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

	private final EvaluacionService service;
	
	@PostMapping("/inscripcion/{id}")
	public EvaluacionResponseDTO grabar(
			@PathVariable Long id, 
			@RequestBody EvaluacionRequestDTO dto) {
		
		var eval = EvaluacionMapper.toEntity(dto);
		var grabado = service.grabar(id, eval);
		return EvaluacionMapper.toDTO(grabado);
	}
	
	@GetMapping
	public List<EvaluacionResponseDTO> listar(){		
		return service.listar()
				.stream()
				.map(EvaluacionMapper::toDTO)
				.toList();
	}
	
	@GetMapping("/tablero/{estudianteId}")
	public List<TableroCursoDTO> tablero(@PathVariable Long estudianteId) {
		return service.obtenerTablero(estudianteId);
	}
}
