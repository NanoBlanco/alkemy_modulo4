package mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import modelos.Moneda;
import modelos.Usuario;
import servicios.UsuarioServicio;

public class TransaccionesTest {
	
	private Moneda m;
	private UsuarioServicio us;
	
	@Test
	public void testDeposito() {
		
		Usuario u = mock(Usuario.class);
		
		us.agregarUsuario(u);
		
		us.depositoUsuario(u, null, 500);
		
		verify(u).getBilletera().depositar(m.CLP, 500);
		
		assertEquals(500, u.getBilletera().getSaldo(m.CLP));
	}

}
