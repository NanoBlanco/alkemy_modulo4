package com.reinaldo.abp.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.reinaldo.abp.dto.evaluacion.EvaluacionesItemDTO;
import com.reinaldo.abp.dto.evaluacion.TableroCursoDTO;
import com.reinaldo.abp.entity.Evaluacion;
import com.reinaldo.abp.exception.RecursoNoEncontradoException;
import com.reinaldo.abp.repository.EvaluacionRepository;
import com.reinaldo.abp.repository.InscripcionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluacionService {

	private final EvaluacionRepository repo;
	private final InscripcionRepository repoIn;
	
	public Evaluacion grabar(Long inscripcionId, Evaluacion eva) {
		var c = repoIn.findById(inscripcionId)
				.orElseThrow(() -> new RecursoNoEncontradoException("Inscripcion no encontrada"));
		eva.setInscripcion(c);
		return repo.save(eva);
	}
	
	public List<Evaluacion> listar(){
		return repo.findAll();
	}
	
	public List<TableroCursoDTO> obtenerTablero(Long estudianteId) {
		
		var listaPlana = repoIn.findDashboardByEstudiante(estudianteId);
		
		Map<Long, TableroCursoDTO> mapa = new LinkedHashMap<>();
		
		for(var row: listaPlana) {
			mapa.putIfAbsent(row.getCursoId(), 
					TableroCursoDTO.builder()
					.cursoId(row.getCursoId())
					.nombreCurso(row.getNombreCurso())
					.evaluaciones(new ArrayList<>())
					.build()
			);
			
			if (row.getEvaluacionId() != null) {
				mapa.get(row.getCursoId()).getEvaluaciones().add(
						EvaluacionesItemDTO.builder()
						.evaluacionId(row.getEvaluacionId())
						.evaluacionNombre(row.getEvaluacionNombre())
						.puntuacion(row.getPuntuacion())
						.build()
				);
			}
		}
		return new ArrayList<>(mapa.values());
	}
}
