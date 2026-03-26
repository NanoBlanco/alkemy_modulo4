package com.reinaldo.abp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.abp.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
	Optional<Estudiante> findByCorreo(String correo);
}
