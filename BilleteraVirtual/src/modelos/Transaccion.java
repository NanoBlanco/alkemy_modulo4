package modelos;

import java.time.LocalDateTime;

public class Transaccion {
	
	private TipoTransaccion tipo;
	private Moneda moneda;
	private int monto;
	private LocalDateTime fecha;
	
	public Transaccion(TipoTransaccion tipo, Moneda moneda, int monto) {
		super();
		this.tipo = tipo;
		this.moneda = moneda;
		this.monto = monto;
		this.fecha = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return  fecha +" - " + tipo + " - " + moneda + " - " + monto;
	}
	
	

}
