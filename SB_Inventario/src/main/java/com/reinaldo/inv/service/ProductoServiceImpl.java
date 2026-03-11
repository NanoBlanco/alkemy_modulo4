package com.reinaldo.inv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reinaldo.inv.model.Categoria;
import com.reinaldo.inv.model.MovimientoStock;
import com.reinaldo.inv.model.Producto;
import com.reinaldo.inv.repository.CategoriaRepository;
import com.reinaldo.inv.repository.ProductoRepository;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	ProductoRepository repoProd;
	@Autowired
	CategoriaRepository repoCat;
	
	@Override
	public List<Producto> listar() {
		return repoProd.findAll();
	}

	@Override
	public Optional<Producto> obtener(Integer id) {
		return Optional.ofNullable(repoProd.findById(id).orElse(null));
	}

	@Override
	public void guardar(Producto p) {
		p.setActivo(true);
		p.setFechaAlta(LocalDateTime.now());
		repoProd.save(p);
	}

	@Override
	public void eliminar(Integer id) {
		repoProd.deleteById(id);
	}

	@Override
	public List<Categoria> listarCategorias() {
		return repoCat.findAll();
	}

}
