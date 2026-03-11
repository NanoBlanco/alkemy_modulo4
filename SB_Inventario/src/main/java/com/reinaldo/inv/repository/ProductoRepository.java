package com.reinaldo.inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.inv.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
