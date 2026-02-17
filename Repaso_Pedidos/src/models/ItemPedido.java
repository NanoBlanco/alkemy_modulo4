package models;

public class ItemPedido {
	
	private Producto producto;
	private int cantidad;
	
	public ItemPedido(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public int subTotal() {
		return cantidad * producto.getPrecio();
	}

	public void mostrar() {
		System.out.println("\n"+producto.getId()+" x"+cantidad+" ="+subTotal());
	}
}
