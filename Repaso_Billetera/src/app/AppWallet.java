package app;


import interfaces.MetodoPago;
import modelos.Moneda;
import modelos.Usuario;
import pagos.PagoTarjeta;
import servicios.WalletService;

public class AppWallet {

	public static void main(String[] args) {
		
		WalletService ws = new WalletService();
		
		ws.agregarUsuario(new Usuario(1, "Andre"));
		ws.agregarUsuario(new Usuario(2, "Claudia"));
		
		MetodoPago metodo = new PagoTarjeta();
		
		metodo.pagar(10000.0, Moneda.CLP);
		
		Usuario u1 = ws.buscarUsuario(1);
		u1.getCuenta().depositar(Moneda.CLP, 10000);
		
		ws.transferir(1, 2, Moneda.CLP, 1000);
		System.out.println("Transferencia Realizada");
		
		System.out.println("Saldo 1: "+u1.getCuenta().getSaldo(Moneda.CLP));
		
		Usuario u2 = ws.buscarUsuario(2);
		
		System.out.println("Saldo 2: "+u2.getCuenta().getSaldo(Moneda.CLP));
	}

}
