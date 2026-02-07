package polimorfismo;

public class Empleado {
	
	protected String nombre;
	protected double salarioBase;
	
	public Empleado(String nombre, double salarioBase) {
		super();
		this.nombre = nombre;
		this.salarioBase = salarioBase;
	}
	
	public double calcularSalario() {
		return salarioBase;
	}
	
	public void trabajar() {
		System.out.println(nombre+" está trabajando.");
	}

}
