package colecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class EjemploLinkedList {

	public static void main(String[] args) {
		LinkedList<String> tareas = new LinkedList<>();
		
		// Agregar
		tareas.add("Estudiar Java");
		tareas.add("Hacer Ejercicios");
		tareas.add("Leer un libro");
		
		System.out.println("Lista inicial: "+tareas);

		// Operaciones especificas
		tareas.addFirst("Despertar");
		tareas.addLast("Dormir");
		System.out.println("Con operaciones extremos: "+tareas);
		
		// Acceder por extremos
		System.out.println("\nPrimera tarea: "+tareas.getFirst());
		System.out.println("Ultima tarea: "+tareas.getLast());
	}

}
