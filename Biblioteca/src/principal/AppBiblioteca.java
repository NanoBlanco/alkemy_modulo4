package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Estudiante;
import modelo.Libro;
import modelo.Prestamo;
import modelo.Profesor;
import modelo.Usuario;

public class AppBiblioteca {

	static Scanner sc = new Scanner(System.in);
	static Map<String, Libro> biblioteca = new HashMap<>();
	static Map<String, Usuario> usuarios = new HashMap<>();
	static List<Prestamo> prestamos = new ArrayList<>() ;
	
	public static void main(String[] args) {
		int opcion;
		
		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			
			switch(opcion) {
				case 1 -> agregarLibro();
				case 2 -> registrarUsuario();
				case 3 -> listarLibros();
				case 4 -> prestarLibro();
				case 5 -> devolverLibro();
				case 0 -> System.out.println("Saliendo...");
				default -> System.out.println("Ingrese una opción válida.");
			}
		}while(opcion != 0);

	}
	
	static void mostrarMenu() {
		System.out.println("\nBiblioteca");
		System.out.println("-".repeat(20));
		System.out.println("1. Agregar libro");
		System.out.println("2. Registrar usuario");
		System.out.println("3. Listar libros");
		System.out.println("4. Prestar libro");
		System.out.println("5. Devolver libro");
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
	
	static void registrarUsuario() {
		System.out.println("Tipo de Usuario: ");
		System.out.println("1. Estudiante / 2. Profesor");
		int tipo = sc.nextInt();

		System.out.println("Id: ");
		String id = sc.next();
		
		if(usuarios.containsKey(id)) {
			System.out.println("Usuario ya registrado.");
			return;
		}
		
		System.out.println("Nombre: ");
		String nombre = sc.next();
		if (tipo == 1) {			
			usuarios.put(id, new Estudiante(id, nombre));
		} else if (tipo == 2) {
			usuarios.put(id, new Profesor(id, nombre));
		}else {
			System.out.println("Debe seleccionar entre uno u otro");
		}
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
				System.out.println("Id Usuario");
				String id = sc.nextLine();
				if(usuarios.containsKey(id)) {
					Usuario prestador = usuarios.get(id);
					if(prestador.puedePedir()) {
						prestador.prestarLibro();
						libro.prestar();
						prestamos.add(new Prestamo(prestador, libro));
						System.out.println("Préstamo Realizado");
					}else {
						System.out.println("No puede pedir más libros, debe regresar uno libro primero");
					}
				}
			}
		}
	}
	
	static void devolverLibro() {
		Prestamo prestamoEncontrado = null;
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
				for(Prestamo p: prestamos) {
					if (p.getLibro().getIsbn().equals(isbn)) {
						prestamoEncontrado = p;
					}
				}
				prestamoEncontrado.getLibro().devolver();
				prestamoEncontrado.getUsuario().devolverLibro();
				prestamos.remove(prestamoEncontrado);
				System.out.println("libro devuelto");
			}
		}
	}

}
