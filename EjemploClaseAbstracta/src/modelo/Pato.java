package modelo;

import interfaces.Caminante;
import interfaces.Nadador;
import interfaces.Volador;

public class Pato implements Caminante, Nadador, Volador {

	private String nombre;
	
	public Pato(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public void volar() {
		System.out.println(nombre+" está volando");

	}

	@Override
	public void aterrizar() {
		System.out.println(nombre+" está aterrizando");

	}

	@Override
	public void nadar() {
		System.out.println(nombre+" está nadando");

	}

	@Override
	public void sumergirse() {
		System.out.println(nombre+" está sumergiendo");

	}

	@Override
	public void caminar() {
		System.out.println(nombre + " está caminando");

	}

}
