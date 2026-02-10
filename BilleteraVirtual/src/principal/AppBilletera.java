package principal;

import java.util.Scanner;

import modelos.Moneda;
import modelos.Usuario;
import servicios.BilleteraServicio;

public class AppBilletera {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		BilleteraServicio sistema = new BilleteraServicio();
		Usuario valentina = new Usuario(1,"Valentina");
		Usuario benjamin = new Usuario(2,"Benjamin");
		
		sistema.agregarUsuario(valentina);
		sistema.agregarUsuario(benjamin);
		
		valentina.getBilletera().depositar(Moneda.CLP, 100000);
		benjamin.getBilletera().depositar(Moneda.CLP, 10000);
		
		int opcion;
		
		do {
			
			System.out.println("\n1. Ver saldo");
			System.out.println("2. Depositar");
			System.out.println("3. Retirar");
			System.out.println("4. Transferir");
			System.out.println("0. Salir");
			
			opcion = sc.nextInt();
			switch(opcion) {
				case 1 -> valentina.getBilletera().mostrarSaldos();
				case 2 -> valentina.getBilletera().depositar(Moneda.CLP, 10000);
				case 3 -> valentina.getBilletera().retirar(Moneda.CLP, 5000);
				case 4 -> sistema.transferir(valentina, benjamin, Moneda.CLP, 1000);
				case 0 -> System.out.println("Saliendo...");
				default -> System.out.println("Ingrese opción válida");
			}
		} while(opcion != 0);
		
		sc.close();

	}

}
