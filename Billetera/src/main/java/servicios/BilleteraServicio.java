package servicios;

import java.util.Scanner;

import dao.BilleteraDAO;
import excepciones.SaldoInsuficienteException;
import modelos.Moneda;
import modelos.Usuario;

public class BilleteraServicio {
	
	private final BilleteraDAO billetera;
	private UsuarioServicio usuario;
	private LogsTransacciones logger;
	
	public BilleteraServicio(BilleteraDAO billetera) {
		this.billetera = billetera;
	}
	
	public void verSaldos() {
		
	}
	
	public void depositar(int id, String mo, int monto) {
		Usuario u = usuario.getUsuario(id);
		if (u!=null) {	
			usuario.depositoUsuario(u, mo, monto);
			logger.escribirLog("INFO", "Deposito realizado a usuarioId "+id+" por un monto "+monto+" a la moneda "+mo);
		}else {
			System.out.println("El Id no existe");
		}
	}
	
	public void retirar(int id, String mo, int monto) throws SaldoInsuficienteException  {
		try {			
			usuario.retiroUsuario(id, mo, monto);
			logger.escribirLog("INFO", "Retiro realizado a usuarioId "+id+" por un monto "+monto+" a la moneda "+mo);
		} catch(SaldoInsuficienteException e) {
			logger.escribirLog("ERROR", "El saldo no es suficiente para el retiro");
			System.out.println("El saldo para la moneda NO es suficiente.");
		}
	}
	
	public void transferir(int id1, int id2, String mo, int monto) {
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
