package principal;

import java.util.List;
import java.util.Scanner;

import modelos.Usuario;
import servicios.BilleteraServicio;
import servicios.UsuarioServicio;

public class AppBilletera {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		BilleteraServicio sistema = new BilleteraServicio();
		UsuarioServicio usuario = new UsuarioServicio();
		
		int opcion;
		
		do {
			System.out.println("\n1. Agregar Usuario");
			System.out.println("2. Depositar");
			System.out.println("3. Retirar");
			System.out.println("4. Transferir");
			System.out.println("5. Ver saldo");
			System.out.println("6. Listar Usuarios");
			System.out.println("0. Salir");
			
			opcion = sc.nextInt();
			switch(opcion) {
				case 1 -> usuario.agregarUsuario(sc);
				case 2 -> sistema.depositar(sc);
				case 3 -> sistema.retirar(sc);
				case 4 -> sistema.transferir(sc);
				case 5 -> sistema.verSaldos(sc);
				case 6 -> {List<Usuario> usuarios = usuario.getUsuarios(); usuarios.forEach(System.out::println);}
				case 0 -> System.out.println("Saliendo...");
				default -> System.out.println("Ingrese opción válida");
			}
		} while(opcion != 0);
		
		sc.close();
	}

}
