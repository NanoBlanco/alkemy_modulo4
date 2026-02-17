package app;

import models.Pedido;
import models.Producto;
import repository.RepositorioEnMemoria;
import service.PedidoService;
import service.ProductoService;

public class AppPedido {

	public static void main(String[] args) {
		
		var repoProductos = new RepositorioEnMemoria<Producto, Integer>();
		var prodService = new ProductoService(repoProductos);
		
		prodService.guardar(new Producto(1, "Mouse", 25000));
		prodService.guardar(new Producto(2, "Teclado", 35000));
		
		var repoPedido = new RepositorioEnMemoria<Pedido, Integer>();
		var pediService = new PedidoService(repoPedido, prodService);
		
		pediService.crearPedido(1);
		
		pediService.agregarProducto(1, 1, 2);
		pediService.agregarProducto(1, 2, 1);
		
		pediService.listar().forEach(p->p.mostrar());
	}

}
