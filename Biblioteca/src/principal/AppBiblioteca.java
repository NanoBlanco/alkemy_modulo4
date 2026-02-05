package principal;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import modelo.Libro;

public class AppBiblioteca {

	static Scanner sc = new Scanner(System.in);
	static Map<String, Libro> biblioteca = new HashMap<>();
	
	public static void main(String[] args) {
		int opcion;
		
		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			
			switch(opcion) {
				case 1 -> agregarLibro();
				case 2 -> listarLibros();
				case 3 -> prestarLibro();
				case 4 -> devolverLibro();
				case 0 -> System.out.println("Saliendo...");
				default -> System.out.println("Ingrese una opción válida.");
			}
		}while(opcion != 0);

	}
	
	static void mostrarMenu() {
		System.out.println("\nBiblioteca");
		System.out.println("-".repeat(20));
		System.out.println("1. Agregar libro");
		System.out.println("2. Listar libros");
		System.out.println("3. Prestar libro");
		System.out.println("4. Devolver libro");
		System.out.println("0. Salir");
	}
	
	static int leerEntero(String mensaje) {
		while(true) {
			try {				
				System.out.println(mensaje);
				return Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				System.out.println("Ingrese un número válido");
			}
		}
	}
	
	static void agregarLibro() {
		System.out.print("ISBN: ");
		String isbn = sc.nextLine();
		
		if(biblioteca.containsKey(isbn)) {
			System.out.println("El libro ya existe.");
			return;
		}
		
		System.out.print("Titulo: ");
		String titulo = sc.nextLine();
		
		System.out.print("Autor: ");
		String autor = sc.nextLine();
		
		biblioteca.put(isbn, new Libro(isbn, titulo, autor));
		System.out.println("Libro Agregado.");
	}
	
	static void listarLibros() {
		if(biblioteca.isEmpty()) {
			System.out.println("No hay libros.");
		}else {
			biblioteca.values().forEach(Libro::mostrarLibro);
		}
	}
	
	static void prestarLibro() {
		System.out.print("ISBN: ");
		String isbn = sc.nextLine();
		
		if(!biblioteca.containsKey(isbn)) {
			System.out.println("El libro NO existe.");
			return;
		}else {
			Libro libro = biblioteca.get(isbn);
			if(libro.isPrestado()) {
				System.out.println("El libro está prestado.");
			}else {
				libro.prestar();
				System.out.println("libro prestado");
			}
		}
	}
	
	static void devolverLibro() {
		System.out.print("ISBN: ");
		String isbn = sc.nextLine();
		
		if(!biblioteca.containsKey(isbn)) {
			System.out.println("El libro NO existe.");
			return;
		}else {
			Libro libro = biblioteca.get(isbn);
			if(!libro.isPrestado()) {
				System.out.println("El libro NO está prestado.");
			}else {
				libro.devolver();
				System.out.println("libro devuelto");
			}
		}
	}

}
