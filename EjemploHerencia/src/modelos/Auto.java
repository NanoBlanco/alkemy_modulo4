package modelos;

public class Auto extends Vehiculo {

	private int numeroPuertas;
	private boolean esAutomatico;
	
	public Auto(String marca, String modelo, int año, int numeroPuertas, boolean esAutomatico) {
		super(marca, modelo, año);
		this.numeroPuertas = numeroPuertas;
		this.esAutomatico = esAutomatico;
	}

	// Sobrescribiendo el método que tiene la clase padre
	@Override
	public void arrancar() {
		System.out.println("El auto marca "+marca+" "+" "+modelo+" está arrancando");
	}
	
	// Métodos propios
	public void abrirMaletero() {
		System.out.println("Abriendo el maletero...");
	}
	
	@Override
	public void mostrarInfo() {
		super.mostrarInfo();
		System.out.println("Numero de puertas: "+numeroPuertas+"\nEs automático: "+(esAutomatico ? "Si": "No"));
	}
}
