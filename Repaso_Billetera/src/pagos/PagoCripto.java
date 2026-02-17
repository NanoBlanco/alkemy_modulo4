package pagos;

import interfaces.MetodoPago;
import modelos.Moneda;

public class PagoCripto implements MetodoPago {

	@Override
	public void pagar(double monto, Moneda m) {
		System.out.println("Pago con Cripto "+monto+" "+m);

	}

}
