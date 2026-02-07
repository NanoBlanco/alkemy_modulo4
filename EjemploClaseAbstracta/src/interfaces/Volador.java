package interfaces;

public interface Volador {
	// Constante
	double VELOCIDAD_MAX = 1000.0;

	// Método abstracto (implícito)
	void volar();
	void aterrizar();
	
	default void despegar() {
		System.out.println("Preparando despegue");
		volar();
	}
	
	static void mostrar() {
		System.out.println("Interfaz para objetos voladores");	
	}
	
	private void verificarCondiciones() {
		System.out.println("Verificando condiciones metereológicas");
	}
}
