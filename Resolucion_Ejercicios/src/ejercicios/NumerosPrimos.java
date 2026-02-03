package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumerosPrimos {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Numeros Primos");
		System.out.println("-".repeat(20));

		System.out.println("Ingresa un numero entero ");
		int n = sc.nextInt();
		System.out.println("Buscando números primos hasta: "+n);
		System.out.println();
		
		//Solucion
		
		List<Integer> primos = new ArrayList<>();
		for(int num = 2; num <= n; num++) {
			if(esPrimo(num)) {
				primos.add(num);
			}
		}
		
		// Mostrar los primos
		System.out.println("Numeros primos encontrados: ");
		System.out.println(primos);
		System.out.println();
		
		//Contar y sumar
		int cantidad = primos.size();
		int suma = 0;
		
		for(int primo: primos) {
			suma += primo;
		}
		
		System.out.println("Estadisticas: ");
		System.out.println("-".repeat(20));
		System.out.println("Cantidad de primos: "+cantidad);
		System.out.println("Suma de todos: "+suma);
		System.out.printf("Promedio: %.2f\n", (double) suma/cantidad);
		System.out.println();
		
		System.out.println("Primos gemelos");
		for(int i = 0; i < primos.size() - 1; i++) {
			if (primos.get(i+1) - primos.get(i) == 2) {
				System.out.println("("+primos.get(i)+", "+ primos.get(i+1)+")");
			}
		}
	}
	
	public static boolean esPrimo(int numero) {
		if (numero < 2) {
			return false;
		}
		for(int i = 2; i <= Math.sqrt(numero); i++) {
			if(numero % i == 0) {
				return false; // Encontramos un divisor, NO es primo
			}
		}
		return true; // No encontramos divisor, ES primo
	}

}
