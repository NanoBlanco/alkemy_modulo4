package principal;

import java.util.HashSet;
import java.util.Scanner;

public class ControlAsistencia {
	
	static HashSet<String> estudiantesPresentes = new HashSet<>();
	static HashSet<String> estudiantes = new HashSet<>();

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opcion;
		registroEstudiantes();

		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			switch(opcion) {
				case 1 -> registrarAsistencia();
				case 2 -> mostrarPresentes();
				case 3 -> mostrarAusentes();
				case 4 -> verificarAsistencia();
				case 5 -> eliminarAsistencia();
				case 6 -> mostrarEstadisticas();
				case 7 -> listarEstudiantes();
				case 0 -> System.out.println("Saliendo...");
				default -> System.out.println("Ingrese una opción válida.");
			}
		}while(opcion != 0);
	}
	
	static void registroEstudiantes() {
		estudiantes.add("Valentina Villarroel");
		estudiantes.add("Sebastian Galaz");
		estudiantes.add("Itala Alvarado");
		estudiantes.add("Daniel Dabed");
		estudiantes.add("Gabriel Ibacache");
		estudiantes.add("Camilo Montalvan");
		estudiantes.add("Andre Moreno");
		estudiantes.add("Benja Palacios");
		estudiantes.add("Renny Blanco");		
	}

	static void mostrarMenu() {
		System.out.println("\nControl de Asistencias");
		System.out.println("-".repeat(30));
		System.out.println("1. Registrar Asistencia");
		System.out.println("2. Mostrar Presentes");
		System.out.println("3. Mostrar Ausentes");
		System.out.println("4. Verificar asistencia");
		System.out.println("5. Eliminar asistencia");
		System.out.println("6. Mostrar Estadística");
		System.out.println("7. Listar todos los estudiantes");
		System.out.println("0. Salir");
	}
	
	static int leerEntero(String mensaje) {
		while(true) {
			try {				
				System.out.println(mensaje);
				return Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				System.out.println("Ingrese un número válido");
			}
		}
	}
	
	static void registrarAsistencia() {
		System.out.print("Ingrese el nombre del estudiante: ");
		String nombre = sc.nextLine();
		
		boolean existe = buscarEstudiante(estudiantes, nombre.toLowerCase()); 
		
		if (existe) {
			boolean agregado = estudiantesPresentes.add(nombre);
			if (agregado) {
				System.out.println("Asistencia registrada para: "+nombre);
			}else {
				System.out.println("El estudiante ya tiene asistenciaregistrada");
			}
		}else {
			System.out.println("Estudiante no encontrado en el sistema.");
		}
	}
	
	static void mostrarPresentes() {
		if(estudiantesPresentes.isEmpty()) {
			System.out.println("No hay estudiantes presentes");
		}else {
			System.out.println("Estudiantes presentes ("+estudiantesPresentes.size()+")");
			for(String est: estudiantesPresentes) {
				System.out.println("[X] "+est);
			}
		}
	}
	
	static void mostrarAusentes() {
		HashSet<String> ausentes = new HashSet<>(estudiantes);
		ausentes.removeAll(estudiantesPresentes);
		
		if(ausentes.isEmpty()) {
			System.out.println("Todos los estudiantes están presentes");
		}else {
			System.out.println("Estudiantes ausentes ("+ausentes.size()+")");
			for(String est: ausentes) {
				System.out.println("[ ] "+est);
			}
		}
	}
	
	static void verificarAsistencia() {
		System.out.print("Ingrese el nombre del estudiante: ");
		String nombre = sc.nextLine();
		
		boolean existe = buscarEstudiante(estudiantes, nombre.toLowerCase()); 
		
		if (existe) {
			if(estudiantesPresentes.contains(nombre)) {
				System.out.println("[X] "+nombre+" está Presente" );
			}else {
				System.out.println("[ ] "+nombre+" está Ausente" );
			}
		}else {
			System.out.println("Estudiante no encontrado en el sistema.");
		}
	}
	
	static void eliminarAsistencia() {
		System.out.print("Ingrese el nombre del estudiante: ");
		String nombre = sc.nextLine();
		
		boolean existe = buscarEstudiante(estudiantes, nombre.toLowerCase()); 
		
		if (existe) {
			boolean eliminado = estudiantesPresentes.remove(nombre);
			if(eliminado) {
				System.out.println("Asistencia eliminada para: "+nombre);
			}else {
				System.out.println("El estudiante "+nombre+" no registra asistencia" );
			}
		}else {
			System.out.println("Estudiante no encontrado en el sistema.");
		}
	}
	
	static void mostrarEstadisticas() {
		int total = estudiantes.size();
		int presentes = estudiantesPresentes.size();
		int ausentes = total-presentes;
		double porcentaje = (presentes * 100) / total;
		System.out.println("\nEstadisticas");
		System.out.println("-".repeat(20));
		System.out.println("Presentes: "+presentes);
		System.out.println("Ausentes: "+ausentes);
		System.out.printf("Porcentaje de asistencia %.2f%%\n ", porcentaje);
	}
	
	static void listarEstudiantes() {
		System.out.println("\nListado de estudiantes");
		for(String est: estudiantes) {
			String estado = estudiantesPresentes.contains(est) ? "[X]" : "[ ]"; 
			System.out.println(est+" "+estado);
		}
	}
	
	static boolean buscarEstudiante(HashSet<String> conjunto, String alumno) {
		for(String est: conjunto) {
			if (est.toLowerCase().equals(alumno)) {
				return true;
			}
		}
		return false;
	}
}
