package app;

import java.util.Scanner;

import modelos.Libro;
import modelos.Usuario;
import servicios.BibliotecaServicio;

public class AppBiblio {

	public static void main(String[] args) {
		
		BibliotecaServicio bs = new BibliotecaServicio();
		Scanner sc = new Scanner(System.in);
		
		bs.agregarLibro(new Libro(1, "Clean code"));
		bs.agregarLibro(new Libro(2, "Effective Java"));
		
		bs.agregarUsuario(new Usuario(1, "Vale"));
		bs.agregarUsuario(new Usuario(2, "Sebas"));

		int opcion;
			
		do {
			System.out.println("\n1. Listar Libros");
			System.out.println("2. Prestar Libros");
			System.out.println("3. Devolver Libros");
			System.out.println("0. Salir");
			
			opcion = sc.nextInt();
			
			try{
			
				switch(opcion) {
					case 1 -> bs.listarLibros();
					case 2 -> {
						System.out.print("Id Libro: ");
						int l = sc.nextInt();
						System.out.print("Id Usuario: ");
						int u = sc.nextInt();
						bs.prestarLibro(l, u);
					}
					case 3 ->{
						System.out.print("Id Libro: ");
						int l = sc.nextInt();
						bs.devolverLibro(l);
					}
					case 0 -> System.out.println("Saliendo...");
					default -> System.out.println("Ingrese una opción válida...");
				}
			}catch(Exception e) {
				System.out.println("Error: "+e.getMessage());
			}
		} while(opcion != 0);
		sc.close();
	}

}
