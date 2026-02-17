package models;

import java.util.ArrayList;
import java.util.List;

import interfaces.Identificable;

public class Pedido implements Identificable<Integer> {

	private Integer id;
	private List<ItemPedido> items = new ArrayList<>();
	
	public Pedido(Integer id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	public void agregarItem(Producto p, int cantidad) {
		items.add(new ItemPedido(p, cantidad));
	}
	
	public int total() {
		return items.stream()
				.mapToInt(ItemPedido::subTotal)
				.sum();
	}
	
	public void mostrar() {
		System.out.println("Pedido "+id);
		items.forEach(ItemPedido::mostrar);
		System.out.println("Total: $"+total());
	}
}
