package interfaces;

import modelos.Moneda;
import modelos.Usuario;

public interface MetodoPago {
	boolean pagar(Usuario u, Moneda m, int monto);
}
