package com.reinaldo.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.gestor.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{
	Rol findByNombre(String nombre);
}
