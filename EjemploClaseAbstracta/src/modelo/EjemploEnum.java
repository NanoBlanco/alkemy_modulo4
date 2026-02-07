package modelo;

public class EjemploEnum {

	public static void main(String[] args) {
		
		NivelPrioridad prioridad = NivelPrioridad.ALTA;
		System.out.println(prioridad.getDescripcion());
		
		switch(prioridad) {
			case BAJA -> System.out.println("Sin prisa");
			case MEDIA -> System.out.println("Planificar");
			case ALTA, CRITICA -> System.out.println("Actuar YA!");
		}

	}

}
