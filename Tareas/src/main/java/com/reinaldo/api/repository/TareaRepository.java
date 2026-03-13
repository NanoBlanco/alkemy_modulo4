package com.reinaldo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.api.entity.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long>{

}
