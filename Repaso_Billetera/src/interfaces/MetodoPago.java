package interfaces;

import modelos.Moneda;

public interface MetodoPago {
	void pagar(double monto, Moneda m);
}
