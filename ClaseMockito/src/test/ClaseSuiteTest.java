package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	TiendaServicioTest.class,
	UsuarioServicioTest.class
})
class ClaseSuiteTest {

}
