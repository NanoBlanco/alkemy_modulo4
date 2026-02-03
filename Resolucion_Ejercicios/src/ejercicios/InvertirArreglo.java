package ejercicios;

import java.util.Arrays;

public class InvertirArreglo {

	public static void main(String[] args) {
		System.out.println("Invertir Arreglo");
		System.out.println("-".repeat(20));
		
		// Datos de entrada
		int[] numeros = {10,20,30,40,50,60,70};
		int[] palindromo = {1,2,3,2,1};
		
		System.out.println("Arreglo original: "+ Arrays.toString(numeros));
		
		boolean esPalindromo = verificarPalindromo(numeros);
		
		int intercambios = invertirArreglo(numeros);
		
		System.out.println("Arreglo invertido: "+ Arrays.toString(numeros));
		System.out.println("Intercambios realizados: "+ intercambios);
		System.out.println("¿Era palindromo? "+esPalindromo);
		
		System.out.println("Arreglo original: "+ Arrays.toString(palindromo));
		
		boolean esPalindromo2 = verificarPalindromo(palindromo);
		
		int intercambios2 = invertirArreglo(palindromo);
		
		System.out.println("Arreglo invertido: "+ Arrays.toString(palindromo));
		System.out.println("Intercambios realizados: "+ intercambios2);
		System.out.println("¿Era palindromo? "+esPalindromo2);
	}
	
	private static int invertirArreglo(int[] arr) {
		int inter = 0;
		int inicio = 0;
		int fin = arr.length - 1;
		
		while(inicio < fin) {
			int temp = arr[inicio];
			arr[inicio] = arr[fin];
			arr[fin] = temp;
			
			inter++;
			inicio++;
			fin--;
		}
		return inter;
	}
	
	private static boolean verificarPalindromo(int[] arr) {
		int inicio = 0;
		int fin = arr.length - 1;
		while(inicio < fin) {
			if(arr[inicio] != arr[fin]) {
				return false;
			}			
			inicio++;
			fin--;
		}
		return true;
	}

}
