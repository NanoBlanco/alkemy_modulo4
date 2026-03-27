package com.reinaldo.abp.mapper;

import org.springframework.stereotype.Component;

import com.reinaldo.abp.dto.inscripcion.InscripcionResponseDTO;
import com.reinaldo.abp.entity.Inscripcion;

@Component
public class InscripcionMapper {

	public static InscripcionResponseDTO toDTO(Inscripcion i) {
		return InscripcionResponseDTO.builder()
				.id(i.getId())
				.estudianteId(i.getEstudiante().getId())
				.cursoId(i.getCurso().getId())
				.fecha_inscripcion(i.getFecha_inscripcion())
				.build();
	}
}
