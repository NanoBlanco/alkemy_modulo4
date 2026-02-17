package modelos;

import interfaces.Identificable;

public class Libro implements Identificable<Integer> {
	
	private Integer id;
	private String titulo;

	
	public Libro(Integer id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}

	@Override
	public Integer getId() {
		return id;
	}

	private void mostrarInfo() {
		System.out.println("\nId "+id+" - "+titulo);
	}
}
