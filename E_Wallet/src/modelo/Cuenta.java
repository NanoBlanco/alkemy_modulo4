package modelo;

public class Cuenta {
	
	private int numeroCuenta;
	private int idTitular;
	private TipoCuenta tipoCuenta;
	private double saldo;
	private static final double SALDO_MINIMO = 0;
	
	public Cuenta() { }
	
	public Cuenta(int cuenta, int id_titular, TipoCuenta tipo, double saldo) {
		this.numeroCuenta = cuenta;
		this.idTitular = id_titular;
		this.tipoCuenta = tipo;
		this.saldo = saldo >= SALDO_MINIMO ? saldo : SALDO_MINIMO;
	}
	
	public int getNumeroCuenta() {
		return this.numeroCuenta;
	}
	
	public void setNumeroCuenta(int cuenta) {
		this.numeroCuenta = cuenta;
	}
	
	public int getTitular() {
		return this.idTitular;
	}
	
	public void setTitular(int id_titular) {
		if(id_titular > 0) {			
			this.idTitular = idTitular;
		}
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	
	public void mostrar() {
		System.out.println("\nNumeroCuenta: " + numeroCuenta +"\nTipo de Cuenta: "+ tipoCuenta + "\nSaldo $" + saldo);
	}
	
	
}
