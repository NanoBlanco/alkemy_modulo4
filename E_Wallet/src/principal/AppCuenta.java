package principal;

import java.util.List;

import modelo.Cliente;
import modelo.Cuenta;
import modelo.TipoCuenta;

public class AppCuenta {

	public static void main(String[] args) {
		
		Cliente cliente1 = new Cliente(1,"Renny Blanco", List.of(new Cuenta(1, 1, TipoCuenta.AHORRO, 100.0), new Cuenta(2,1,TipoCuenta.CORRIENTE, 500.0)));		
		cliente1.getCuenta().get(0).depositar(50);
		cliente1.mostrarCliente();
		
		Cliente cliente2 = new Cliente(2,"Sebastian Galaz", List.of(new Cuenta(3, 2, TipoCuenta.AHORRO, 100.0)));
		cliente2.mostrarCliente();
	}

}
