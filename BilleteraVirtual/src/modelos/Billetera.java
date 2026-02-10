package modelos;

import java.util.HashMap;
import java.util.Map;

public class Billetera {
	
	private Map<Moneda, Integer> saldos = new HashMap<>();
	
	public Billetera() {
		for (Moneda m : Moneda.values()) {
			saldos.put(m, 0);
		}
	}
	
	public void depositar(Moneda m, int monto) {
		saldos.put(m, saldos.get(m)+monto);
	}
	
	public boolean retirar(Moneda m, int monto) {
		int saldo = saldos.get(m);
		if(saldo < monto) return false;
		
		saldos.put(m, saldo-monto);
		return true;
	}
	
	public int getSaldo(Moneda m) {
		return saldos.get(m);
	}
	
	public void mostrarSaldos() {
		saldos.forEach((m,s)->System.out.println(m+" : "+s));
	}
}
