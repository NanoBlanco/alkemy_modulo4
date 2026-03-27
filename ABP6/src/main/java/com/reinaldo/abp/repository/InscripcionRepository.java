package com.reinaldo.abp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reinaldo.abp.entity.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long >{

	@Query("""
			SELECT 
			c.id as cursoId,
			c.nombre as nombreCurso,
			e.id as evaluacionId,
			e.nombre as evaluacionNombre,
			e.puntuacion as puntuacion
			FROM Inscripcion in
			JOIN in.curso c
			LEFT JOIN in.evaluaciones e
			WHERE in.estudiante.id = :estudianteId
			""")
	List<ProyeccionTableroCurso> findDashboardByEstudiante(@Param("estudianteId") Long estudianteId);
}
