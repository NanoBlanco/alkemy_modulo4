package polimorfismo;

public class Gerente extends Empleado {

	private double bono;
	
	public Gerente(String nombre, double salarioBase, double bono) {
		super(nombre, salarioBase);
		this.bono = bono;
	}

	@Override
	public double calcularSalario() {
		return salarioBase + bono;
	}
	
	@Override
	public void trabajar() {
		System.out.println(nombre+" está gestionando el equipo");
	}
}
