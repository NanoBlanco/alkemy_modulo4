package modelos;

public class Moto extends Vehiculo {

	private boolean tieneSidecar;
	
	public Moto(String marca, String modelo, int año, boolean sidecar) {
		super(marca, modelo, año);
		this.tieneSidecar = sidecar;
	}

	// Sobrescribiendo el método que tiene la clase padre
	@Override
	public void arrancar() {
		System.out.println("La moto está arrancando...");
	}
	
	public void hacerCaballito() {
		System.out.println("¡Haciendo Caballito!");
	}

}
