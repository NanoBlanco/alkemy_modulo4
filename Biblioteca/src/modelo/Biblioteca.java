package modelo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	
	private List<MaterialBiblioteca> catalogo;
	
	public Biblioteca() {
		catalogo = new ArrayList<>();
	}
	
	public void agregarMaterial(MaterialBiblioteca material) {
		catalogo.add(material);
		System.out.println("Material agregado: "+material.getTitulo());
	}

	public void listarDisponibles() {
		System.out.println("Material Disponible");
		System.out.println("-".repeat(20));
		for(MaterialBiblioteca material : catalogo) {
			if(material.estaDisponible()) {
				material.mostrarDetalles();
				System.out.println();
			}
		}
	}
	
	public MaterialBiblioteca buscarPorCodigo(String codigo) {
		for(MaterialBiblioteca material : catalogo) {
			if(material.getCodigo().equals(codigo)) {
				return material;
			}
		}
		return null;
	}
	
	
}
