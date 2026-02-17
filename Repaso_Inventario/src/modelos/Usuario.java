package modelos;

import interfaces.Identificable;

public class Usuario implements Identificable<Integer> {
	
	private Integer id;
	private String nombre;

	public Usuario(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void mostrarInfo() {
		System.out.println("\nId "+id+" - "+nombre);
	}
}
