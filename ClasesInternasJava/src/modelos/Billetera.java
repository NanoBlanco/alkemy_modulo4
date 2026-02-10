package modelos;

public class Billetera {
	
	private int saldo = 100000;

	public class TarjetaVirtual { // Inner Class
		
		public void pagar(int monto) {
			
			class Comprobante{ // Local Class
				void imprimir() {
					System.out.println("Comprobante No 1");
				}
			}
			
			if (saldo >= monto) {
				saldo -= monto;
				Comprobante c = new Comprobante();
				System.out.println("Pago Realizado!");
				c.imprimir();
			}else {
				System.out.println("Saldo Insuficiente");
			}
		}
	}
}
