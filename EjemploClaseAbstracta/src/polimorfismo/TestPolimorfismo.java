package polimorfismo;

public class TestPolimorfismo {

	public static void main(String[] args) {
		Empleado emp1 = new Programador("Valentina", 600, 1500);
		Empleado emp2 = new Gerente("Sebastian", 800, 200);
		Empleado emp3 = new Empleado("Camilo",600);

		Empleado[] empleados = {emp1, emp2, emp3};
		
		for(Empleado emp: empleados) {
			emp.trabajar();
			System.out.println("Salario $"+emp.calcularSalario());
			System.out.println("-".repeat(10));
		}
	}

}
