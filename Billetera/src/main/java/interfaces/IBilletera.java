package interfaces;

import modelos.Moneda;

public interface IBilletera {
	void generarBilletera(int id);
	void transaccion(int id, String m, int monto, String operacion);
	void transferir(int origen_id, int destino_id, Moneda m, int monto);
	int obtenerSaldo(int id);
}
