package modelos;

import interfaces.MetodoPago;

public class PagoConTarjeta implements MetodoPago {

	@Override
	public boolean pagar(Usuario u, Moneda m, int monto) {
		System.out.println("Pago aprobaod con tarjeta por "+monto);
		return true;
	}

}
