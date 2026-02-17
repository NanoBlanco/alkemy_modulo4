package pagos;

import interfaces.MetodoPago;
import modelos.Moneda;

public class PagoTransferencia implements MetodoPago {

	@Override
	public void pagar(double monto, Moneda m) {
		System.out.println("Pago por transferencia: "+monto+" "+m);

	}

}
