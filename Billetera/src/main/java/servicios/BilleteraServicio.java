package servicios;

import java.util.Scanner;

import excepciones.SaldoInsuficienteException;
import modelos.Moneda;
import modelos.Usuario;

public class BilleteraServicio {
	
	UsuarioServicio usuario;
	LogsTransacciones logger;
	
	public BilleteraServicio(UsuarioServicio usuario, LogsTransacciones logger) {
		this.usuario = usuario;
		this.logger = logger;
	}
	
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
		Usuario u = usuario.getUsuario(id);
		if (u!=null) {	
			usuario.depositoUsuario(u, mo, monto);
			logger.escribirLog("INFO", "Deposito realizado a usuarioId "+id+" por un monto "+monto+" a la moneda "+mo);
		}else {
			System.out.println("El Id no existe");
		}
	}
	
	public void retirar(Scanner sc) throws SaldoInsuficienteException  {
		try {			
			int id = leerId(sc);
			String mo = leerMoneda(sc);		
			int monto = leerMonto(sc);
			usuario.retiroUsuario(id, mo, monto);
			logger.escribirLog("INFO", "Retiro realizado a usuarioId "+id+" por un monto "+monto+" a la moneda "+mo);
		} catch(SaldoInsuficienteException e) {
			logger.escribirLog("ERROR", "El saldo no es suficiente para el retiro");
			System.out.println("El saldo para la moneda NO es suficiente.");
		}
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
			logger.escribirLog("ERROR", "Tranferencia NO realizada a usuario "+destino.getNombre()+" por un monto "+monto+" a la moneda "+m);
			System.out.println("Saldo Insuficiente.");
			return;
		}
		
		destino.getBilletera().depositar(m, monto);
		logger.escribirLog("INFO", "Tranferencia realizada a usuario "+destino.getNombre()+" por un monto "+monto+" a la moneda "+m);
		System.out.println("Transferencia realizada.");
	}

}
