import java.util.*;

/**
 * PLANTILLAS PARA PRACTICAR
 * 
 * Este archivo contiene las plantillas de los 5 ejercicios para que
 * puedas completarlos por tu cuenta sin ver las soluciones.
 * 
 * INSTRUCCIONES:
 * 1. Lee el enunciado en ENUNCIADOS_EJERCICIOS.md
 * 2. Consulta la GUIA_TEORICA.md si necesitas ayuda
 * 3. Completa el código en las secciones marcadas con TODO
 * 4. Compara tu solución con EjerciciosBasicos.java
 */
public class PlantillasEjercicios {
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║        EJERCICIOS BÁSICOS - PLANTILLAS               ║");
        System.out.println("║          Completa el código por tu cuenta            ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        ejercicio1_PromedioCalificaciones();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        ejercicio2_NumerosPrimos();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        ejercicio3_InvertirArreglo();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        ejercicio4_GestionEstudiantes();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        ejercicio5_EstadisticasVentas();
    }
    
    /**
     * EJERCICIO 1: PROMEDIO DE CALIFICACIONES
     * 
     * Completa este ejercicio calculando:
     * - Promedio de calificaciones
     * - Cantidad de aprobados y reprobados
     * - Calificación máxima y mínima
     * - Estudiantes sobre el promedio
     */
    private static void ejercicio1_PromedioCalificaciones() {
        System.out.println("EJERCICIO 1: PROMEDIO DE CALIFICACIONES");
        System.out.println("─".repeat(60));
        
        // Datos de entrada
        int[] calificaciones = {85, 92, 78, 65, 45, 90, 73, 88, 55, 95};
        
        System.out.println("Calificaciones: " + Arrays.toString(calificaciones));
        System.out.println();
        
        // TODO: Paso 1 - Calcular el promedio
        double suma = 0;
        // Tu código aquí: recorre el arreglo y suma las calificaciones
        
        double promedio = 0; // Calcula el promedio
        
        // TODO: Paso 2 - Contar aprobados (>= 60) y reprobados (< 60)
        int aprobados = 0;
        int reprobados = 0;
        // Tu código aquí: usa un ciclo y condicionales
        
        
        // TODO: Paso 3 - Encontrar calificación máxima y mínima
        int maxima = 0;  // Inicializa correctamente
        int minima = 0;  // Inicializa correctamente
        // Tu código aquí: recorre y compara
        
        
        // TODO: Paso 4 - Mostrar resultados
        System.out.println("RESULTADOS:");
        System.out.println("─".repeat(40));
        // Imprime: promedio, aprobados, reprobados, máxima, mínima
        
        
        // TODO: Paso 5 - Mostrar estudiantes sobre el promedio
        System.out.println("\nEstudiantes sobre el promedio:");
        // Recorre y compara cada calificación con el promedio
        
    }
    
    /**
     * EJERCICIO 2: NÚMEROS PRIMOS
     * 
     * Completa este ejercicio para:
     * - Encontrar todos los primos hasta N
     * - Calcular suma y estadísticas
     * - Identificar primos gemelos
     */
    private static void ejercicio2_NumerosPrimos() {
        System.out.println("EJERCICIO 2: NÚMEROS PRIMOS");
        System.out.println("─".repeat(60));
        
        int n = 50;
        System.out.println("Buscando números primos hasta: " + n);
        System.out.println();
        
        // TODO: Paso 1 - Encontrar todos los primos
        List<Integer> primos = new ArrayList<>();
        // Tu código aquí: usa un ciclo y el método esPrimo()
        
        
        // TODO: Paso 2 - Mostrar los primos
        System.out.println("Números primos encontrados:");
        // Imprime la lista de primos
        
        
        // TODO: Paso 3 - Calcular cantidad y suma
        int cantidad = 0;
        int suma = 0;
        // Recorre la lista y suma
        
        
        System.out.println("\nESTADÍSTICAS:");
        System.out.println("─".repeat(40));
        // Imprime cantidad, suma y promedio
        
        
        // TODO: Paso 4 - Encontrar primos gemelos (diferencia de 2)
        System.out.println("\nPrimos gemelos (diferencia de 2):");
        // Recorre la lista comparando elementos consecutivos
        
    }
    
    /**
     * TODO: Completa este método para verificar si un número es primo
     * Un número es primo si solo es divisible por 1 y por sí mismo
     */
    private static boolean esPrimo(int numero) {
        // TODO: Implementa la lógica
        // Pista: verifica divisores desde 2 hasta la raíz cuadrada
        
        return false; // Cambia esto
    }
    
    /**
     * EJERCICIO 3: INVERTIR ARREGLO
     * 
     * Completa este ejercicio para:
     * - Invertir un arreglo sin usar espacio adicional
     * - Verificar si es palíndromo
     * - Contar intercambios
     */
    private static void ejercicio3_InvertirArreglo() {
        System.out.println("EJERCICIO 3: INVERTIR ARREGLO");
        System.out.println("─".repeat(60));
        
        int[] numeros = {10, 20, 30, 40, 50, 60, 70};
        int[] palindromo = {1, 2, 3, 2, 1};
        
        // TODO: Ejemplo 1 - Invertir arreglo normal
        System.out.println("EJEMPLO 1 - Arreglo normal:");
        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        
        // Verifica si es palíndromo ANTES de invertir
        boolean esPalindromo1 = false; // Usa el método verificarPalindromo()
        
        // Invierte el arreglo
        int intercambios1 = 0; // Usa el método invertirArreglo()
        
        System.out.println("Arreglo invertido: " + Arrays.toString(numeros));
        // Imprime intercambios y si era palíndromo
        
        
        // TODO: Ejemplo 2 - Arreglo palíndromo
        System.out.println("\nEJEMPLO 2 - Arreglo palíndromo:");
        // Repite el proceso con el arreglo palindromo
        
    }
    
    /**
     * TODO: Completa este método para invertir un arreglo in-place
     * Debe retornar el número de intercambios realizados
     */
    private static int invertirArreglo(int[] arr) {
        int intercambios = 0;
        
        // TODO: Usa dos índices: inicio y fin
        // Intercambia elementos y mueve los índices
        
        
        return intercambios;
    }
    
    /**
     * TODO: Completa este método para verificar si un arreglo es palíndromo
     */
    private static boolean verificarPalindromo(int[] arr) {
        // TODO: Compara elementos desde los extremos hacia el centro
        
        
        return false; // Cambia esto
    }
    
    /**
     * EJERCICIO 4: GESTIÓN DE ESTUDIANTES
     * 
     * Completa este ejercicio para:
     * - Gestionar nombres y calificaciones en listas paralelas
     * - Buscar estudiantes
     * - Mostrar ranking
     * - Calcular estadísticas por letra
     */
    private static void ejercicio4_GestionEstudiantes() {
        System.out.println("EJERCICIO 4: GESTIÓN DE ESTUDIANTES");
        System.out.println("─".repeat(60));
        
        // Listas paralelas
        List<String> nombres = new ArrayList<>();
        List<Integer> calificaciones = new ArrayList<>();
        
        // TODO: Agrega los estudiantes usando el método agregarEstudiante()
        // Ana García - 95
        // Carlos López - 78
        // María Rodríguez - 88
        // Pedro Martínez - 65
        // Laura Sánchez - 92
        // Diego Torres - 45
        // Sofia Ramírez - 83
        
        
        System.out.println("Total de estudiantes: " + nombres.size());
        System.out.println();
        
        // TODO: Paso 1 - Mostrar todos los estudiantes
        System.out.println("LISTA DE ESTUDIANTES:");
        System.out.println("─".repeat(40));
        // Recorre las listas y muestra cada estudiante con su calificación
        
        
        // TODO: Paso 2 - Buscar un estudiante
        String nombreBuscar = "María Rodríguez";
        // Usa el método buscarEstudiante()
        
        
        // TODO: Paso 3 - Encontrar mejor y peor estudiante
        
        
        // TODO: Paso 4 - Mostrar distribución por letra (A, B, C, D, F)
        System.out.println("DISTRIBUCIÓN POR CALIFICACIÓN:");
        System.out.println("─".repeat(40));
        // Cuenta cuántos tienen A (90-100), B (80-89), etc.
        
        
        // TODO: Paso 5 - Mostrar ranking ordenado
        System.out.println("\nRANKING DE ESTUDIANTES:");
        System.out.println("─".repeat(40));
        // Ordena las listas por calificación (descendente)
        
    }
    
    /**
     * TODO: Completa este método para agregar un estudiante
     */
    private static void agregarEstudiante(List<String> nombres, 
                                         List<Integer> calificaciones,
                                         String nombre,
                                         int calificacion) {
        // Agrega a ambas listas
        
    }
    
    /**
     * TODO: Completa este método para buscar un estudiante por nombre
     * Debe retornar el índice donde está, o -1 si no se encuentra
     */
    private static int buscarEstudiante(List<String> nombres, String nombre) {
        // Recorre la lista de nombres
        
        
        return -1; // No encontrado
    }
    
    /**
     * TODO: Completa este método para obtener la letra según la calificación
     * A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: 0-59
     */
    private static String obtenerLetra(int calificacion) {
        // Usa if-else o switch
        
        
        return "F"; // Cambia esto
    }
    
    /**
     * EJERCICIO 5: ESTADÍSTICAS DE VENTAS
     * 
     * Completa este ejercicio para:
     * - Calcular total y promedio de ventas
     * - Encontrar mejor y peor día
     * - Detectar tendencias
     * - Hacer proyecciones
     */
    private static void ejercicio5_EstadisticasVentas() {
        System.out.println("EJERCICIO 5: ESTADÍSTICAS DE VENTAS");
        System.out.println("─".repeat(60));
        
        // Datos de entrada
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", 
                        "Viernes", "Sábado", "Domingo"};
        double[] ventas = {1250.50, 1380.75, 1420.30, 1560.90, 
                          1890.45, 2340.80, 2150.60};
        
        System.out.println("VENTAS DE LA SEMANA (en miles de pesos):");
        System.out.println("─".repeat(40));
        // TODO: Muestra cada día con su venta
        
        
        // TODO: Paso 1 - Calcular total y promedio
        double totalVentas = 0;
        // Suma todas las ventas
        
        double promedio = 0; // Calcula el promedio
        
        System.out.println("\nANÁLISIS GENERAL:");
        System.out.println("─".repeat(40));
        // Imprime total y promedio con formato
        
        
        // TODO: Paso 2 - Encontrar mejor y peor día
        
        
        // TODO: Paso 3 - Días sobre el promedio
        System.out.println("\nDÍAS SOBRE EL PROMEDIO:");
        System.out.println("─".repeat(40));
        
        
        // TODO: Paso 4 - Detectar tendencia
        System.out.println("\nANÁLISIS DE TENDENCIA:");
        System.out.println("─".repeat(40));
        // Cuenta días con crecimiento vs decrecimiento
        
        
        // TODO: Paso 5 - Proyección para el próximo mes
        System.out.println("\nPROYECCIÓN PARA EL PRÓXIMO MES:");
        System.out.println("─".repeat(40));
        // Calcula tasa de crecimiento y proyección
        
        
        // TODO: Paso 6 - Gráfico de barras simple
        System.out.println("\nGRÁFICO DE VENTAS:");
        System.out.println("─".repeat(40));
        // Usa █ para representar las ventas visualmente
        
    }
}
