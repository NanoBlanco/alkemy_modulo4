package principal;

public class TiendaServicio {

	private ServicioPago pago;
	
	public TiendaServicio(ServicioPago pago) {
		this.pago = pago;
	}

	public boolean comprar(int monto) {
		return pago.procesarPago(monto);
	}

}
