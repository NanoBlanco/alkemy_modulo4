package modelos;

public class Usuario {
	
	private String nombre;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	// Static Nested Class
	static class Validador {
		public static boolean nombreValido(String nombre) {
			return nombre != null && nombre.length() >= 3;
		}
	}
	
	public boolean ok = Usuario.Validador.nombreValido("Ana");
}



