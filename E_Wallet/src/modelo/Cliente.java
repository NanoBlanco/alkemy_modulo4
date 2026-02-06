package modelo;

import java.util.List;

public class Cliente {
	
	private int id;
	private String nombre;
	private List<Cuenta> cuentas;
	
	public Cliente(int id, String nombre, List<Cuenta> cuentas) {
		this.id = id;
		this.nombre = nombre;
		this.cuentas = cuentas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cuenta> getCuenta() {
		return cuentas;
	}

	public void addCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
	
	public void mostrarCliente() {
		System.out.println("\nId: "+id+"\nNombre: "+nombre+"\nDatos Cuenta: ");
		for(Cuenta c : cuentas) {
			c.mostrar();
		}
	}

}
