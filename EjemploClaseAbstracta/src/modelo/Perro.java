package modelo;

public class Perro extends Animal {

	public Perro(String nombre, int edad) {
		super(nombre, edad);
	}
	

	@Override
	public void hacerSonido() {
		System.out.println(nombre+" está ladrando...");
		
	}

}
