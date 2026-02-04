package colecciones;

import java.util.ArrayList;
import java.util.Collections;

public class EjemploArrayList {

	public static void main(String[] args) {
		
		ArrayList<String> ciudades = new ArrayList<>();
		
		// Agregar
		ciudades.add("Santiago");
		ciudades.add("Concepcion");
		ciudades.add("Arica");
		ciudades.add("Puerto Mont");
		ciudades.add("Valparaiso");
		ciudades.add("Arica"); // Permite duplicados
		
		System.out.println("Lista original: "+ciudades);
		System.out.println("Tamaño: "+ciudades.size());
		
		// Acceso por indice
		System.out.println("\nPrimer elemento: "+ciudades.get(0));
		System.out.println("Ultimo elemento: "+ciudades.get(ciudades.size()-1));
		
		// Insertar en posicion especifica
		ciudades.add(4,"Pucon");
		System.out.println("Despues de insertar en pos 4: "+ciudades);
		
		// Modificar
		ciudades.set(0, "RM");
		
		// Buscar elemento
		int idx = ciudades.indexOf("Arica");
		System.out.println("\nPrimera aparición de Arica: "+idx);
		System.out.println("¿contiene viña del mar: "+ciudades.contains("viña del mar"));
		
		// Eliminar por indice
		ciudades.remove(ciudades.size()-1);
		System.out.println("\nDespues de eliminar ulitmo indice: "+ciudades);
		
		// Eliminar por objeto
		ciudades.remove("Pucon");
		System.out.println("\nDespues de eliminar Pucon: "+ciudades);
		
		// Recorrer
		for(int i = 0; i < ciudades.size(); i++) {
			System.out.print(ciudades.get(i)+", ");
		}
		System.out.println();
		for(String ciudad: ciudades) {
			System.out.println(ciudad+", ");
		}
		
		ciudades.stream().forEach(System.out::println);
		
		// Ordenar
		Collections.sort(ciudades);
		System.out.println("\nLista Ordenada: "+ciudades);
		
		// Sub-listas
		ArrayList<String> sublista = new ArrayList<>(ciudades.subList(1, 2));
		System.out.println("Sublista: "+sublista);
	}

}
