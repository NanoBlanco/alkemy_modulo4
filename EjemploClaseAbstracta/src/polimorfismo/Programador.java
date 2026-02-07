package polimorfismo;

public class Programador extends Empleado {

	private int lineasCodigo;
	
	public Programador(String nombre, double salarioBase, int lineas) {
		super(nombre, salarioBase);
		this.lineasCodigo = lineas;
	}

	@Override
	public double calcularSalario() {
		return salarioBase * (lineasCodigo * 0.5);
	}
	
	@Override
	public void trabajar() {
		System.out.println(nombre+" está programando");
	}
}
