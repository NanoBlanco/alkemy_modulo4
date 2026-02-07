package modelo;

public class Revista extends MaterialBiblioteca {

	private int numeroEdicion;
	private String mes;
	
	public Revista(String codigo, String titulo, int añoPublicacion, int edicion, String mes) {
		super(codigo, titulo, añoPublicacion);
		this.numeroEdicion = edicion;
		this.mes = mes;
	}

	@Override
	public void mostrarDetalles() {
		System.out.println("Revista");
		System.out.println("\nCodigo: "+codigo+"\nTitulo: "+titulo+"\nEdicion "+numeroEdicion+"\nMes "+mes+"\nAño de Publicacion "+añoPublicacion+"\nEstado "+estado.getDescripcion());
	}

}
