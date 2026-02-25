package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import excepciones.SaldoInsuficienteException;
import modelos.Moneda;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class UsuarioServicioTest {
	
	Usuario u;
	UsuarioServicio us;
	Moneda m;
	
	@BeforeEach
	void setUp() {
		//u = new Usuario(1, "Vale");
		//us = new UsuarioServicio(u);
	}

	@Test
	@DisplayName("Verifica deposito")
	void testDeposito() {
		us.depositoUsuario(u, "CLP", 500);
		int saldo = u.getBilletera().getSaldo(Moneda.CLP);
		
		assertEquals(500, saldo);
	}
	
	@Test
	@DisplayName("Realiza Retiro Correctamente")
	void testRetiro() throws SaldoInsuficienteException {
		us.depositoUsuario(u, "CLP", 500);
		us.retiroUsuario(1, "CLP", 100);
		int saldo = u.getBilletera().getSaldo(Moneda.CLP);
		
		assertEquals(400, saldo);
	}
	
	@Test
	@DisplayName("No debe permitir Retiro")
	void testRetiroIncorrecto() throws SaldoInsuficienteException {
		assertThrows(SaldoInsuficienteException.class, ()-> {
			us.depositoUsuario(u, "CLP", 500);
			us.retiroUsuario(1, "CLP", 600);
		});
	}
	
}
