package enums;

public enum Prioridad {
	NORMAL, URGENTE;
	
	public static Prioridad desdeOpcion(int opcion) {
		return switch(opcion) {
		case 1 -> NORMAL;
		case 2 -> URGENTE;
		default -> throw new IllegalArgumentException("Opción de prioridad inválida");
		};
	}
}
