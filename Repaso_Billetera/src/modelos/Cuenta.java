package modelos;

import java.util.HashMap;
import java.util.Map;

public class Cuenta {
	
	private Map<Moneda, Double> saldos = new HashMap<>();
	
	public Cuenta() {
		for(Moneda m : Moneda.values()) {
			saldos.put(m, 0.0);
		}
	}
	
	public void depositar(Moneda m, double monto){
		saldos.put(m, saldos.get(m)+monto);
	}
	
	public void retirar(Moneda m, double monto) {
		double saldo = saldos.get(m);
		
		if (saldo < monto) 
			throw new IllegalStateException("Saldo Insuficiente");
		
		saldos.put(m, saldo-monto);
	}
	
	public double getSaldo(Moneda m) {
		return saldos.get(m);
	}

}
