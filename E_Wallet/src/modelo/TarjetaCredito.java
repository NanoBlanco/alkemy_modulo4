package modelo;

import java.util.Scanner;

public class TarjetaCredito extends FormaPago {

	private int cuotas;
	static Scanner sc = new Scanner(System.in);
	
	public TarjetaCredito(int cuotas) {
		this.cuotas = cuotas;
	}
	
	@Override
	void realizarPago() {
		System.out.print("Numero de cuotas: ");
		cuotas = sc.nextInt();
	}

}
