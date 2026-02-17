package models;

import interfaces.Identificable;

public class Producto implements Identificable<Integer> {

	private Integer id;
	private String nombre;
	private int precio;
	
	public Producto(Integer id, String nombre, int precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public int getPrecio() {
		return precio;
	}
	
	public void mostrar() {
		System.out.println("\nId "+id+" - "+nombre+" $"+precio);
	}
}
