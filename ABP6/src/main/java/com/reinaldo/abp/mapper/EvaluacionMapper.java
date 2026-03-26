package com.reinaldo.abp.mapper;

import org.springframework.stereotype.Component;

import com.reinaldo.abp.dto.evaluacion.EvaluacionRequestDTO;
import com.reinaldo.abp.dto.evaluacion.EvaluacionResponseDTO;
import com.reinaldo.abp.entity.Evaluacion;

@Component
public class EvaluacionMapper {

	public static Evaluacion toEntity(EvaluacionRequestDTO dto) {
		return Evaluacion.builder()
				.nombre(dto.nombre())
				.puntuacion(dto.puntuacion())
				.build();
	}
	
	public static EvaluacionResponseDTO toDTO(Evaluacion entity) {
		return EvaluacionResponseDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.puntuacion(entity.getPuntuacion())
				.cursoId(entity.getCurso() != null ? entity.getCurso().getId() : null)
				.build();
	}
}
