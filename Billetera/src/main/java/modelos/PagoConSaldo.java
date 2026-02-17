package modelos;

import interfaces.MetodoPago;

public class PagoConSaldo implements MetodoPago {

	@Override
	public boolean pagar(Usuario u, Moneda m, int monto) {
		return u.getBilletera().retirar(m, monto);
	}

}
