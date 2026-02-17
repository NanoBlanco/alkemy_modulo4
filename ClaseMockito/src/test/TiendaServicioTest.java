package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import principal.ServicioPago;
import principal.TiendaServicio;

class TiendaServicioTest {

	@Test
	void testCompra() {
		
		ServicioPago pagoMock = mock(ServicioPago.class);
		when(pagoMock.procesarPago(100)).thenReturn(true);
		
		TiendaServicio tienda = new TiendaServicio(pagoMock);
		
		assertTrue(tienda.comprar(100));
	}
	
	
}
