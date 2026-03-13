package com.reinaldo.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.reinaldo.api.entity.Producto;

import jakarta.persistence.criteria.Predicate;

public class ProductoSpecification {
	
	public static Specification<Producto> filtro(
			String nombre, 
			Long categoriaId, 
			Integer minPrecio, 
			Integer maxPrecio){

		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(nombre != null) {
				predicates.add(cb.like(cb.lower(root.get("nombre")), "%"+nombre.toLowerCase()+"%"));
			}
			
			if(categoriaId != null) {
				predicates.add(cb.equal(root.get("id").get("id"), categoriaId));
			}
			
			if(minPrecio != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("precio"), minPrecio));
			}
			
			if(maxPrecio != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("precio"), maxPrecio));
			}
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};
		
	}

}
