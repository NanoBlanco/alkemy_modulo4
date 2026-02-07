package modelo;

public enum NivelPrioridad {
	BAJA(1, "Puede esperar"),
	MEDIA(2, "Atender Pronto"),
	ALTA(3, "Urgente"),
	CRITICA(4, "Inmediato");

	private final int valor;
	private final String descripcion;
	
	NivelPrioridad(int valor, String desc){
		this.valor = valor;
		this.descripcion = desc;
	}
	
	public int getValor() {
		return valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public boolean esMayorQue(NivelPrioridad otro) {
		return this.valor > otro.valor;
	}
}
