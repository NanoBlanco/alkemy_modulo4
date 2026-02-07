package modelo;

import java.util.Scanner;

public class Moneda extends FormaPago {

	TipoMoneda moneda;
	static Scanner sc = new Scanner(System.in);
	
	@Override
	void realizarPago() {
		System.out.print("Escoja la moneda: CLP$, USD$, R$ ");
		String tipo = sc.nextLine().toUpperCase();
		
		if(moneda.esValido(tipo)) {
			System.out.println("Moneda Correcta");
		}else {
			System.out.println("Moneda Incorrecta");
		}
	}

}
