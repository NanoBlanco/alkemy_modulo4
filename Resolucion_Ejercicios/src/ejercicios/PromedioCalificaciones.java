package ejercicios;

import java.util.Arrays;

public class PromedioCalificaciones {

	public static void main(String[] args) {
		System.out.println("Promedio de Calificaciones");
		System.out.println("-".repeat(40));
		
		// datos de entrada
		int[] calificaciones = {85,92,70,65,45,90,73,88,55,95,36};
		
		System.out.println("Calificaciones: "+Arrays.toString(calificaciones));
		System.out.println();
		
		// soluciones
		// promedio, aprobados, reprobados, maxima y minima
		double suma = 0;
		int aprobados = 0;
		int reprobados = 0;
		int maxima = calificaciones[0];
		int minima = calificaciones[0];
		
		for(int i = 0; i < calificaciones.length; i++) {
			suma += calificaciones[i];
			if (calificaciones[i] >= 60) {
				aprobados++;
			}else {
				reprobados++;
			}
			if (calificaciones[i] > maxima) {
				maxima = calificaciones[i];
			}
			if (calificaciones[i] < minima) {
				minima = calificaciones[i];
			}
		}
		
		double promedio = suma / calificaciones.length;
		
		System.out.println("Resultados:");
		System.out.println("-".repeat(15));
		System.out.printf("Promedio General : %.2f\n", promedio);
		System.out.println("Aprobados: "+aprobados);
		System.out.println("Reprobados: "+reprobados);
		System.out.println("Calificación máxima: "+maxima);
		System.out.println("Calificación minima: "+minima);
		
		System.out.println("Estudiantes sobre el promedio: ");
		for(int i = 0; i < calificaciones.length; i++) {
			if (calificaciones[i] > promedio) {
				System.out.printf(" Estudiante %d: %d puntos \n", i+1, calificaciones[i]);
			}
		}
	}

}
