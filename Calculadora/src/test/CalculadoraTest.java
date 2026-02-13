package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import principal.Calculadora;

class CalculadoraTest {
	
	private Calculadora calc; 
	
	@BeforeEach
	void setUp() {
		calc = new Calculadora();
	}

	@Test
	@DisplayName("Verifica el método sumar")
	void testSumar() {
		
		int resultado = calc.sumar(2, 3);
		
		assertEquals(5, resultado);
	}
	
	@Test
	@DisplayName("Verifica el método multiplicar")
	void testMultiplicar() {
		
		int resultado = calc.multiplicar(2, 3);
		
		assertEquals(6, resultado);
	}

	@Test
	@DisplayName("Verifica método dividir")
	void dividir() {
		assertThrows(ArithmeticException.class, ()-> {
			calc.dividir(5,0);
		});
	}
}
