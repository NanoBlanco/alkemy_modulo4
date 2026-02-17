package service;

import java.util.List;

import models.Producto;
import repository.Repository;

public class ProductoService {
	
	private Repository<Producto, Integer> repo;
	
	public ProductoService(Repository<Producto, Integer> repo) {
		this.repo = repo;
	}
	
	public void guardar(Producto p) {
		repo.save(p);
	}
	
	public Producto buscar(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Producto> listar(){
		return repo.findAll();
	}
}
