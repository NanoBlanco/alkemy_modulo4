package com.reinaldo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.api.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
