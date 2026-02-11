package servicios;

import java.util.Scanner;

import modelos.Moneda;
import modelos.Usuario;

public class BilleteraServicio {
	
	UsuarioServicio usuario = new UsuarioServicio();
	
	private int leerId(Scanner sc) {		
		System.out.println("Ingrese Id del usuario: ");
		int id = sc.nextInt();
		return id;
	}
	 
	private String leerMoneda(Scanner sc) {
		System.out.println("Ingrese la Moneda CLP, USD, EUR, R ");
		String mo = sc.next().toUpperCase();
		return mo;
	}
	
	private int leerMonto(Scanner sc) {
		System.out.println("Ingrese el monto ");
		int monto = sc.nextInt();
		return monto;
	}
	
	public void verSaldos(Scanner sc) {
		int id = leerId(sc);
		usuario.saldosUsuario(id);
	}
	
	public void depositar(Scanner sc) {
		int id = leerId(sc);
		String mo = leerMoneda(sc);
		int monto = leerMonto(sc);
		usuario.operacionesUsuario(id, 1, mo, monto);
	}
	
	public void retirar(Scanner sc) {
		int id = leerId(sc);
		String mo = leerMoneda(sc);		
		int monto = leerMonto(sc);
		usuario.operacionesUsuario(id, 2, mo, monto);
	}
	
	public void transferir(Scanner sc) {
		int id1 = leerId(sc);
		int id2 = leerId(sc);
		String mo = leerMoneda(sc);
		int monto = leerMonto(sc);
		Usuario u1 = usuario.getUsuario(id1);
		Usuario u2 = usuario.getUsuario(id2);
		accionTransferir(u1, u2, Moneda.valueOf(mo), monto);
	}

	public void accionTransferir(Usuario origen, Usuario destino, Moneda m, int monto) {
		boolean ok = origen.getBilletera().retirar(m, monto);
		
		if(!ok) {
			System.out.println("Saldo Insuficiente.");
			return;
		}
		
		destino.getBilletera().depositar(m, monto);
		System.out.println("Transferencia realizada.");
	}

}
