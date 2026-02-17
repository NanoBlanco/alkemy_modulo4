package modelos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Billetera {
	
	private Map<Moneda, Integer> saldos = new HashMap<>();
	
	public Billetera() {
		for (Moneda m : Moneda.values()) {
			saldos.put(m, 0);
		}
	}
	
	public void depositar(Moneda m, int monto) {
		saldos.put(m, saldos.get(m)+monto);
		System.out.println("Depósito realizado.");
	}
	
	public boolean retirar(Moneda m, int monto) {
		int saldo = saldos.get(m);
		if(saldo < monto) return false;
		
		saldos.put(m, saldo-monto);
		System.out.println("Retiro Realizado.");
		return true;
	}
	
	public int getSaldo(Moneda m) {
		return saldos.get(m);
	}
	
	public void mostrarSaldos() {
		saldos.forEach((m,s)->System.out.println(m+" : "+s));
	}

	@Override
	public int hashCode() {
		return Objects.hash(saldos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Billetera other = (Billetera) obj;
		return Objects.equals(saldos, other.saldos);
	}
	
	
}
