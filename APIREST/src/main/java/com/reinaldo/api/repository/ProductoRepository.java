package com.reinaldo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.api.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
