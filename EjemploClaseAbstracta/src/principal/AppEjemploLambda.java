package principal;

import interfaces.Operacion;

public class AppEjemploLambda {

	public static void main(String[] args) {
		Operacion suma = (a, b) -> a + b;
		Operacion multiplicacion = (a, b) -> a * b;

		System.out.println(suma.calcular(5, 3));
		System.out.println(multiplicacion.calcular(5, 3));
	}

}
