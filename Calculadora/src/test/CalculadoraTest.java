package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import principal.Calculadora;

class CalculadoraTest {
	
	private Calculadora calc; 
	
	@BeforeEach
	void setUp() {
		calc = new Calculadora();
	}

	@ParameterizedTest
	@ValueSource(ints = {1,2,5,10})
	void positivos(int n) {
		assertTrue(n > 0);
	}
	
	@ParameterizedTest(name = "{0} + {1} debe ser {2}")
	@CsvSource({
		"2,3,5",
		"1,1,2",
		"5,5,10"
	})
	@DisplayName("Verifica el método sumar")
	void testSumar(int a, int b, int esperado) {
		
		int resultado = calc.sumar(a, b);
		
		assertEquals(esperado, resultado);
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
	
	@AfterEach
	void tearDown() {
		calc = null;
	}
}
