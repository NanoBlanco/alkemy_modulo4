package modelo;

import enums.TipoUsuario;

public class Estudiante extends Usuario {

	public Estudiante(int id, String nombre) {
		super(id, nombre, TipoUsuario.ESTUDIANTE);
	}
	
	@Override
	public String obtenerRol() {
		return "Estudiante";
	}

}
