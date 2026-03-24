package enums;

public enum TipoUsuario {
	ESTUDIANTE, TRABAJADOR; 
	
	public static TipoUsuario desdeOpcion(int opcion) {
		return switch(opcion) {
		case 1 -> ESTUDIANTE;
		case 2 -> TRABAJADOR;
		default -> throw new IllegalArgumentException("Opción de usuario inválida");
		};
	}

}
