package principal;

import java.util.Scanner;
/*
 * @author: Reny Blanco
 * @version: 1.0.0
 * 
 * */
public class Calculadora {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int num1 = 0;
		int num2 = 0;
		
		int resultado = 0;
		String accionUsuario = "";
		String operador = "";
		
		System.out.println("Bienvenidos ");
		do {
			try {
				System.out.println("Ingrese el primer numero");
				num1 = sc.nextInt();
				
				System.out.println("Ingrese el segundo numero");
				num2 = sc.nextInt();
				
				System.out.println("Ingrese el operador");
				operador = sc.next();
				switch(operador) {
					case "+" -> resultado = Calculadora.sumar(num1,num2);
					case "-" -> resultado = Calculadora.restar(num1,num2);
					case "*" -> resultado = Calculadora.multiplicar(num1,num2);
					case "/" -> resultado = Calculadora.dividir(num1,num2);
					default -> System.out.println("Ingrese un operador valido");
				}
				
				if (operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/") ) {
					System.out.println("El resultado es: "+resultado);
				}
				
			} catch (Exception e) {
				System.out.println("Error "+e);
			}finally {
				System.out.println("-".repeat(30));
				System.out.println("stop para detener o s para continuar");
				accionUsuario = sc.next().toLowerCase();
			}
		} while(!accionUsuario.equals("stop"));
		System.out.println("Calculadora Finalizada");
	}
	
	public static int sumar(int a, int b ) {
		return a+b;
	}
	
	public static int restar(int a, int b ) {
		return a-b;
	}
	
	public static int multiplicar(int a, int b ) {
		return a*b;
	}

	public static int dividir(int a, int b ) throws ArithmeticException {
		return a/b;
	}
}
