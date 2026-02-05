package modelo;

public class Cuenta {
	
	private static int contador = 0; // Variable de clase
	private int numeroCuenta;
	private String titular;
	private double saldo;
	private static final double SALDO_MINIMO = 0;
	
	public Cuenta() { }
	
	public Cuenta(int cuenta, String nombre, double saldo) {
		this.numeroCuenta = cuenta;
		this.titular = nombre;
		this.saldo = saldo >= SALDO_MINIMO ? saldo : SALDO_MINIMO;
		contador++;
	}

	public static int getContador() {
		return contador;
	}
	
	public int getNumeroCuenta() {
		return this.numeroCuenta;
	}
	
	public void setNumeroCuenta(int cuenta) {
		this.numeroCuenta = cuenta;
	}
	
	public String getTitular() {
		return this.titular;
	}
	
	public void setTitular(String nombre) {
		if(nombre != null && !nombre.trim().isEmpty()) {			
			this.titular = nombre;
		}
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	// Métodos de negocio
	public boolean depositar(double monto) {
		if (monto > 0) {
			this.saldo += monto;
			return true;
		}
		return false;
	}
	
	public boolean retirar(double monto) {
		if (monto > 0 && (this.saldo - monto) >= SALDO_MINIMO) {
			this.saldo-= monto;
			return true;
		}
		return false;
	}
	
	public String mostrar() {
		return "\nNumeroCuenta: " + numeroCuenta + "\nTitular: " + titular + "\nSaldo $" + saldo;
	}
	
	
}
