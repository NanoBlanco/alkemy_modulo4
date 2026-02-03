package ejercicios;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase de ejemplo para demostrar el uso de JavaDoc.
 * <p>
 * Esta clase contiene métodos simples que muestran cómo usar
 * JavaDoc para generar documentación automática.
 * </p>
 * @author Reny Blanco
 * @version 1.0
 * @since 2026-01-30
 */
public class GestorEstudiantes {
	/**
	 * Método principal.
	 * Funciona como orquestador del ejercicio.
	 */
	public static void main(String[] args) {
		System.out.println("Gestor de estudiantes");
		System.out.println("-".repeat(30));
		
		/**
		 * Listado de estudiantes.
		 * Este campo almacena los nombres completo de los estudiantes.
		 */
		List<String> nombres = new ArrayList<>();
		List<Integer> calificaciones = new ArrayList<>();
		
		// Agrega Estudiantes
		agregaEstudiante(nombres, calificaciones, "Valentina Villarroel", 95);
		agregaEstudiante(nombres, calificaciones, "Camilo Montalban", 80);
		agregaEstudiante(nombres, calificaciones, "Sebastian Galaz", 88);
		agregaEstudiante(nombres, calificaciones, "Andre Moreno", 65);
		agregaEstudiante(nombres, calificaciones, "Daniel  Dabed", 92);
		agregaEstudiante(nombres, calificaciones, "Benjamin Palacios", 83);
		agregaEstudiante(nombres, calificaciones, "Reny Blanco", 78);

		
		System.out.println("Total de estudiantes: "+nombres.size());
		System.out.println();
		
		System.out.println("Lista de estudiantes");
		System.out.println("-".repeat(40));
		mostrarEstudiantes(nombres, calificaciones);
		System.out.println();
		
		// Buscar estudiante
		String nombreBuscar = "Andre Moreno";
		System.out.println("Buscando a: "+nombreBuscar);
		int indice = buscarEstudiante(nombres, nombreBuscar);
		
		if(indice != -1) {
			System.out.println("Encontrado: "+nombres.get(indice)+ "calificacion "+calificaciones.get(indice));
		}else {
			System.out.println("No encontado");
		}
		
		System.out.println();
		
		//mejor y peor
		int peor = 0;
		int mejor = 0;
		
		for(int i = 0; i < calificaciones.size(); i ++) {
			if(calificaciones.get(i) < calificaciones.get(peor)) {
				peor = i;
			}
			if(calificaciones.get(i) > calificaciones.get(mejor)) {
				mejor = i;
			}
		}
		
		System.out.println("Mejor estudiante: "+nombres.get(mejor)+": "+calificaciones.get(mejor));
		System.out.println("Estudiante con más dificultad: "+nombres.get(peor)+": "+calificaciones.get(peor));
		System.out.println();
		
		// estadistica por letra
		System.out.println("Distribucion por nota:");
		System.out.println("-".repeat(40));
		mostrarDistribucion(calificaciones);
		System.out.println();
		
		//Ranking
		System.out.println("Ranking de estudiantes:");
		System.out.println("-".repeat(40));
		mostrarRanking(nombres, calificaciones);
	}
	
	/**
	 * Inserta los estudiantes y sus calificaciones.
	 * en listas separadas 
	 * @param nombres listado de alumnos.
	 * @param calificaciones listado de calificaciones.
	 * @param nombre nombre a agregar.
	 * @param nota calificación del alumno a agregar.
	 */
	private static void agregaEstudiante(List<String> nombres, List<Integer> calificaciones, String nombre, int nota) {
		nombres.add(nombre);
		calificaciones.add(nota);
	}
	
	private static void mostrarEstudiantes(List<String> nombres, List<Integer> calificaciones) {
		for(int i = 0; i < nombres.size()-1; i++) {
			char letra = obtenerLetra(calificaciones.get(i));
			System.out.printf("%2d. %-20s %3d (%s)\n", i+1, nombres.get(i), calificaciones.get(i), letra);
		}
	}
	
	private static char obtenerLetra(int nota) {
		if(nota >= 90) return 'A';
		else if(nota >= 80) return 'B';
		else if(nota >= 70) return 'C';
		else if(nota >= 60) return 'D';
		else return 'F'; 
	}
	
	private static int buscarEstudiante(List<String> nombres, String nombre) {
		for(int x = 0; x < nombres.size() -1; x++) {
			if(nombres.get(x).equalsIgnoreCase(nombre)) {
				return x;
			}
		}
		return -1;
	}
	
	private static void mostrarDistribucion(List<Integer> calificaciones) {
		int cont_A = 0, cont_B = 0, cont_C = 0, cont_D = 0, cont_F = 0;
		
		for(int nota: calificaciones) {
			char letra = obtenerLetra(nota);
			switch(letra) {
			case 'A' -> cont_A++;
			case 'B' -> cont_B++;
			case 'C' -> cont_C++;
			case 'D' -> cont_D++;
			case 'F' -> cont_F++;
			}
		}
		System.out.println("A (90-100): "+ cont_A+ " estudiantes "+ crearBarra(cont_A));
		System.out.println("B (80-89): "+ cont_B+ " estudiantes "+ crearBarra(cont_B));
		System.out.println("C (70-79): "+ cont_C+ " estudiantes "+ crearBarra(cont_C));
		System.out.println("D (60-69): "+ cont_D+ " estudiantes "+ crearBarra(cont_D));
		System.out.println("F (0-59): "+ cont_F+ " estudiantes "+ crearBarra(cont_F));
	}
	
	private static String crearBarra(int cant) {
		return "█".repeat(cant);
	}

	private static void mostrarRanking(List<String> nombres, List<Integer> calificaciones) {
		List<String> nombresOrdenados = new ArrayList<>(nombres);
		List<Integer> notasOrdenadas = new ArrayList<>(calificaciones);
		
		// Burbuja
		for(int i = 0; i < notasOrdenadas.size()-1; i++) {
			for(int j = 0; j < notasOrdenadas.size()-1-i; j++) {
				int tempCal = notasOrdenadas.get(j);
				notasOrdenadas.set(j, notasOrdenadas.get(j+1));
				notasOrdenadas.set(j+1, tempCal);
				
				String nomTemp = nombresOrdenados.get(j);
				nombresOrdenados.set(j, nombresOrdenados.get(j+1));
				nombresOrdenados.set(j+1, nomTemp);
			}
		}
		
		for(int i = 0; i < nombresOrdenados.size()-1; i++) {
			int posicion = 0;
			if(i == 0) posicion = 1;
			else if(i == 1) posicion = 2;
			else if(i == 3) posicion = 3;
			
			System.out.printf("%2d. %-20s %3d puntos %d\n", i+1, nombresOrdenados.get(i), notasOrdenadas.get(i),posicion);
		}
	}
}
