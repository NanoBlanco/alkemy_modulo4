package modelo;

import enums.TipoUsuario;

public class Trabajador extends Usuario {

	public Trabajador(int id, String nombre) {
		super(id, nombre, TipoUsuario.TRABAJADOR);
	}

	@Override
	public String obtenerRol() {
		return "Empleado / Funcionario";
	}
}
