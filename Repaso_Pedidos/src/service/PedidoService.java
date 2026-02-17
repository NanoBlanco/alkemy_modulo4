package service;

import java.util.List;

import models.Pedido;
import models.Producto;
import repository.Repository;

public class PedidoService {
	
	private Repository<Pedido, Integer> repo;
	private ProductoService ps;
	
	public PedidoService(
			Repository<Pedido, Integer> repo,
			ProductoService ps
			) {
		this.repo = repo;
		this.ps = ps;
	}

	public void crearPedido(int id) {
		repo.save(new Pedido(id));
	}
	
	public void agregarProducto(int pedidoId, int prodId, int c) {
		Pedido ped = repo.findById(pedidoId).orElseThrow(()-> new IllegalArgumentException("Pedido no encontrado"));
		
		Producto p = ps.buscar(prodId);
		
		ped.agregarItem(p, c);
	}
	
	public List<Pedido> listar(){
		return repo.findAll();
	}
}
