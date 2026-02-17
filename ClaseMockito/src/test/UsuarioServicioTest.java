package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import repositorio.UsuarioRepository;
import servicios.UsuarioServicio;

public class UsuarioServicioTest {
	
	@Test
	public void testObtenerNombre() {
		
		UsuarioRepository repoMock = mock(UsuarioRepository.class);
		when(repoMock.buscarUsuario(1)).thenReturn("Valentina");
		
		UsuarioServicio servicio = new UsuarioServicio(repoMock);
		
		String nombre = servicio.obtenerNombre(1);
		
		assertEquals("Valentina", nombre);
		
		verify(repoMock).buscarUsuario(1);
	}

}
