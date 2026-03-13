package com.reinaldo.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.reinaldo.api.dto.ProductoRequestDTO;
import com.reinaldo.api.entity.Categoria;
import com.reinaldo.api.entity.Producto;
import com.reinaldo.api.exception.ResourceNotFoundException;
import com.reinaldo.api.repository.CategoriaRepository;
import com.reinaldo.api.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

	private final ProductoRepository repoProd;
	private final CategoriaRepository repoCat;
	
	@Override
	public Producto crear(ProductoRequestDTO dto) {
		Categoria ct = repoCat.findById(dto.getCategoriaId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
		
		Producto prod = Producto.builder()
				.nombre(dto.getNombre())
				.precio(dto.getPrecio())
				.stock(dto.getStock())
				.categoria(ct)
				.build();

		return repoProd.save(prod);
	}
	
	@Override
	public Producto obtenerPorId(Long id) {
		return repoProd.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
	}
	
	@Override
	public Page<Producto> listar(String nombre, Long categoria, Integer minPrecio, Integer maxPrecio,
			Pageable pageable) {
		
		Specification<Producto> spec = ProductoSpecification.filtro(nombre, categoria, minPrecio, maxPrecio);
		
		return repoProd.findAll(spec, pageable);
	}


	@Override
	public Producto actualizar(Long id, ProductoRequestDTO dto) {
		Producto p = obtenerPorId(id);
		
		Categoria cat = repoCat.findById(dto.getCategoriaId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
		
		p.setNombre(dto.getNombre());
		p.setPrecio(dto.getPrecio());
		p.setStock(dto.getStock());
		p.setCategoria(cat);
		return repoProd.save(p);
	}


	@Override
	public void eliminar(Long id) {
		Producto p = obtenerPorId(id);
		repoProd.delete(p);
	}


	
	
}
