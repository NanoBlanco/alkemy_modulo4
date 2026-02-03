package principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicación de consola para gestión de tareas
 * 
 * Requisitos:
 *  - Agregar tarea
 *  - Ver tareas
 *  - Marcar como completada
 *  - Salir
 *  
 * Se incluyó adicionalmente;
 *  - Eliminar tarea
 *  - Ordenar tareas
 */
public class GestorTareas {
	
	static Scanner sc = new Scanner(System.in);
	
	// Estructuras para almacenar
	static List<String> tareas = new ArrayList<>();
	static List<Boolean> completadas = new ArrayList<>();

	public static void main(String[] args) {
		
		int opcion;
		
		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			
			switch(opcion) {
				case 1 -> agregarTarea();
				case 2 -> mostrarTareas();
				case 3 -> marcarCompletada();
				case 4 -> eliminarTarea();
				case 5 -> ordenarTareas();
				case 6 -> System.out.println("Saliendo...");
				default -> System.out.println("Opción inválida.");
			}
		}while (opcion != 6);

	}
	
	static void mostrarMenu() {
		System.out.println("Gestor de Tareas");
		System.out.println("-".repeat(30));
		System.out.println("1. Agregar tarea");
		System.out.println("2. Ver tareas");
		System.out.println("3. Marcar tarea como completada");
		System.out.println("4. Eliminar tarea");
		System.out.println("5. Ordenar tareas");
		System.out.println("6. Salir");
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
	
	static void agregarTarea() {
		System.out.print("Ingrese la tarea ");
		String tarea = sc.nextLine();
		
		tareas.add(tarea);
		completadas.add(false);
		
		System.out.println("Tarea agregada.\n");
	}
	
	static void mostrarTareas() {
		if (tareas.isEmpty()) {
			System.out.println("No hay tareas registradas.");
			return;
		}
		
		System.out.println("\nListado de tareas");
		for(int i = 0; i < tareas.size(); i++) {
			String estado = completadas.get(i) ? "[X]" : "[ ]";
			System.out.printf("%d. %s %s%n",i+1,estado, tareas.get(i));
		}
		
	}
	
	static void marcarCompletada() {
		mostrarTareas();
		if (tareas.isEmpty()) return;
		
		int indice = leerEntero("Numero de tarea a completar: ") -1;
		if (indiceValido(indice)) {
			completadas.set(indice, true);
			System.out.println("Tarea marcada como completada");
		} else {
			System.out.println("Indice inválido");
		}
				
	}
	
	static void eliminarTarea() {
		mostrarTareas();
		if (tareas.isEmpty()) return;
		int indice = leerEntero("Numero de tarea a eliminar: ") -1;
		
		if (indiceValido(indice)) {
			tareas.remove(indice);
			completadas.remove(indice);
			System.out.println("Tarea eliminada");
		} else {
			System.out.println("Indice inválido");
		}
	}
	
	static boolean indiceValido(int i) {
		return i >= 0 && i < tareas.size();
	}
	
	static void ordenarTareas() {
		if (tareas.isEmpty()) {
			System.out.println("No hay tareas a ordenar.");
			return;
		}
		
		// Indices ordenados segun el texto
		List<Integer> indices = new ArrayList<>();
		for (int i = 0; i < tareas.size(); i++) {
			indices.add(i);
		}
		
		indices.sort(Comparator.comparing(tareas::get));
		
		// Nuevas Listas
		
		List<String> tareasOrdenadas = new ArrayList<>();
		List<Boolean> completadasOrdenadas = new ArrayList<>();
		
		for (int idx: indices) {
			tareasOrdenadas.add(tareas.get(idx));
			completadasOrdenadas.add(completadas.get(idx));
		}
		
		tareas = tareasOrdenadas;
		completadas = completadasOrdenadas;
		
		System.out.println("Tareas ordenadas alfabeticamente");
		mostrarTareas();
	}

}
