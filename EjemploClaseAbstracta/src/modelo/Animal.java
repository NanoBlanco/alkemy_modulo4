package modelo;

public abstract class Animal {
	
	protected String nombre;
	protected int edad;
	
	public Animal(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	// Método abstracto
	public abstract void hacerSonido();
		
	// Método concreto
	public void dormir() {
		System.out.println(nombre+" está durmiendo...");
	}
	
	public String getNombre() {
		return nombre;
	}
}
