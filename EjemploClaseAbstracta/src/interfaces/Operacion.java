package interfaces;

@FunctionalInterface
public interface Operacion {
	int calcular(int a, int b);
	
	default void mostrar() {
		System.out.println("Operación matemática");
	}
}
