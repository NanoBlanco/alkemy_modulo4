package principal;

import modelo.Cuenta;

public class AppCuenta {

	public static void main(String[] args) {
		
		Cuenta cuenta1 = new Cuenta(1,"Renny Blanco",999.99);
		System.out.println("Objeto creado "+cuenta1.getNumeroCuenta()+ " - "+cuenta1.getTitular()+" -> $"+cuenta1.getSaldo());
		
		Cuenta cuenta2 = new Cuenta();
		cuenta2.setNumeroCuenta(2);
		cuenta2.setTitular("Camilo Montalvan");
		cuenta2.setSaldo(100.00);
		
		System.out.println("Objeto creado "+cuenta2.getNumeroCuenta()+ " - "+cuenta2.getTitular()+" -> $"+cuenta2.getSaldo());
		
		Cuenta cuenta3 = new Cuenta(3,"Valentina Villarroel", 200.00);
		System.out.println("Cuenta creada "+cuenta3.mostrar());
	}

}
