package modelo;

import interfaces.Prestable;

public abstract class MaterialBiblioteca implements Prestable {
	
	protected String codigo;
	protected String titulo;
	protected int añoPublicacion;
	protected EstadoMaterial estado;
	protected String usuarioPrestamo;
	
	public MaterialBiblioteca(String codigo, String titulo, int añoPublicacion) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.añoPublicacion = añoPublicacion;
		this.estado = EstadoMaterial.DISPONIBLE;
	}
	
	// Método Abstracto
	public abstract void mostrarDetalles();
	
	@Override
	public boolean prestar(String usuario) {
		if(estado == EstadoMaterial.DISPONIBLE) {
			estado = EstadoMaterial.PRESTADO;
			usuarioPrestamo = usuario;
			System.out.println("Material prestado a "+usuario);
			return true;
		}
		System.out.println("Material no disponible: "+ estado.getDescripcion());
		return false;
	}
	
	@Override
	public boolean devolver() {
		if (estado == EstadoMaterial.PRESTADO) {
			estado = EstadoMaterial.DISPONIBLE;
			System.out.println("Material devuelto por "+usuarioPrestamo);
			usuarioPrestamo = null;
			return true;
		}
		System.out.println("el material no estaba prestado");
		return false;
	}
	
	@Override
	public boolean estaDisponible() {
		return estado == EstadoMaterial.DISPONIBLE;
	}
	
	public void enviarAReparacion() {
		estado = EstadoMaterial.EN_REPARACION;
		System.out.println("Material enviado a reparación");
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public EstadoMaterial getEstado() {
		return estado;
	}
}
