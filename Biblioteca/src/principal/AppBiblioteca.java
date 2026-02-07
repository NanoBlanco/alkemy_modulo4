package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Estudiante;
import modelo.Libro;
import modelo.MaterialBiblioteca;
import modelo.Prestamo;
import modelo.Profesor;
import modelo.Revista;
import modelo.Usuario;

public class AppBiblioteca {

	static Scanner sc = new Scanner(System.in);
	static Map<String, MaterialBiblioteca> biblioteca = new HashMap<>();
	static Map<String, Usuario> usuarios = new HashMap<>();
	static List<Prestamo> prestamos = new ArrayList<>() ;
	
	public static void main(String[] args) {
		int opcion;
		
		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			
			switch(opcion) {
				case 1 -> agregarMaterial();
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
		System.out.println("1. Agregar Material");
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
	
	static void agregarMaterial() {
		System.out.println("Tipo de Material");
		System.out.println("1. Libro / 2. Revista");
		int tipoMat = sc.nextInt();
		
		if (tipoMat == 1) {
			System.out.print("Codigo: ");
			String codigo = sc.next();

			if(biblioteca.containsKey(codigo)) {
				System.out.println("El libro ya existe.");
				return;
			}

			System.out.print("Titulo: ");
			String titulo = sc.next();

			System.out.print("Año de Publicacion");
			int año = sc.nextInt();
			
			System.out.print("Autor: ");
			String autor = sc.next();	
			
			System.out.print("ISBN: ");
			String isbn = sc.next();

			System.out.print("Numero de Paginas: ");
			int paginas = sc.nextInt();
			
			biblioteca.put(codigo, new Libro(codigo, titulo, año, isbn, autor,  paginas));
			System.out.println("Libro Agregado.");	
			
		} else if (tipoMat == 2) {
			System.out.print("Codigo: ");
			String codigo = sc.next();

			if(biblioteca.containsKey(codigo)) {
				System.out.println("El libro ya existe.");
				return;
			}

			System.out.print("Titulo: ");
			String titulo = sc.next();

			System.out.print("Año de Publicacion");
			int año = sc.nextInt();
			
			System.out.print("Edicion: ");
			int edicion = sc.nextInt();	
			
			System.out.print("Mes: ");
			String mes = sc.next();

			biblioteca.put(codigo, new Revista(codigo, titulo, año, edicion, mes));
			System.out.println("Revista Agregada.");	
			
		}else {
			System.out.println("Debe seleccionar entre uno u otro");
		}
		
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
			System.out.println("No hay Material.");
		}else {
			biblioteca.values().forEach(MaterialBiblioteca::mostrarDetalles);
		}
	}
	
	static void prestarLibro() {
		System.out.print("Codigo: ");
		String codigo = sc.nextLine();
		
		if(!biblioteca.containsKey(codigo)) {
			System.out.println("El material NO existe.");
			return;
		}else {
			MaterialBiblioteca libro = biblioteca.get(codigo);
			if(!libro.estaDisponible()) {
				System.out.println("El Material está prestado.");
			}else {
				System.out.println("Id Usuario");
				String id = sc.nextLine();
				if(usuarios.containsKey(id)) {
					Usuario prestador = usuarios.get(id);
					if(prestador.puedePedir()) {
						prestador.prestarLibro();
						libro.prestar(prestador.getNombre());
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
		System.out.print("Codigo: ");
		String codigo = sc.nextLine();
		
		if(!biblioteca.containsKey(codigo)) {
			System.out.println("El material NO existe.");
			return;
		}else {
			MaterialBiblioteca libro = biblioteca.get(codigo);
			if(libro.estaDisponible()) {
				System.out.println("El material NO está prestado.");
			}else {
				for(Prestamo p: prestamos) {
					if (p.getLibro().getCodigo().equals(codigo)) {
						prestamoEncontrado = p;
					}
				}
				prestamoEncontrado.getLibro().devolver();
				prestamoEncontrado.getUsuario().devolverLibro();
				prestamos.remove(prestamoEncontrado);
				System.out.println("Material devuelto");
			}
		}
	}

}
