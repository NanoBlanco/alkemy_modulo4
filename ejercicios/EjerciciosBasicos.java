import java.util.*;

/**
 * EJERCICIOS BÁSICOS DE PROGRAMACIÓN EN JAVA
 * 
 * Este archivo contiene 5 ejercicios prácticos que cubren:
 * - Ciclos (for, while, do-while)
 * - Arreglos (arrays)
 * - Condicionales (if, else, switch)
 * - Listas (ArrayList)
 * 
 * Cada ejercicio incluye:
 * - Enunciado detallado
 * - Solución completa y comentada
 * - Ejemplos de prueba
 * - Explicación del algoritmo
 */
public class EjerciciosBasicos {
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║        EJERCICIOS BÁSICOS DE PROGRAMACIÓN            ║");
        System.out.println("║    Ciclos, Arreglos, Condicionales y Listas          ║");
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
     * ╔═══════════════════════════════════════════════════════════════════════╗
     * ║                         EJERCICIO 1                                   ║
     * ║              PROMEDIO DE CALIFICACIONES                               ║
     * ╚═══════════════════════════════════════════════════════════════════════╝
     * 
     * ENUNCIADO:
     * Escribe un programa que:
     * 1. Reciba un arreglo de calificaciones de estudiantes (números del 0 al 100)
     * 2. Calcule el promedio de todas las calificaciones
     * 3. Determine cuántos estudiantes aprobaron (calificación >= 60)
     * 4. Determine cuántos estudiantes reprobaron (calificación < 60)
     * 5. Encuentre la calificación más alta y más baja
     * 6. Muestre qué estudiantes están por encima del promedio
     * 
     * CONCEPTOS UTILIZADOS:
     * - Arreglos
     * - Ciclo for
     * - Condicionales if-else
     * - Operaciones matemáticas
     */
    private static void ejercicio1_PromedioCalificaciones() {
        System.out.println("EJERCICIO 1: PROMEDIO DE CALIFICACIONES");
        System.out.println("─".repeat(60));
        
        // Datos de entrada: calificaciones de 10 estudiantes
        int[] calificaciones = {85, 92, 78, 65, 45, 90, 73, 88, 55, 95};
        
        System.out.println("Calificaciones: " + Arrays.toString(calificaciones));
        System.out.println();
        
        // SOLUCIÓN:
        
        // Paso 1: Calcular el promedio
        double suma = 0;
        for (int i = 0; i < calificaciones.length; i++) {
            suma += calificaciones[i];
        }
        double promedio = suma / calificaciones.length;
        
        // Paso 2: Contar aprobados y reprobados
        int aprobados = 0;
        int reprobados = 0;
        
        for (int calificacion : calificaciones) {
            if (calificacion >= 60) {
                aprobados++;
            } else {
                reprobados++;
            }
        }
        
        // Paso 3: Encontrar calificación máxima y mínima
        int maxima = calificaciones[0];
        int minima = calificaciones[0];
        
        for (int i = 1; i < calificaciones.length; i++) {
            if (calificaciones[i] > maxima) {
                maxima = calificaciones[i];
            }
            if (calificaciones[i] < minima) {
                minima = calificaciones[i];
            }
        }
        
        // Paso 4: Identificar estudiantes sobre el promedio
        System.out.println("RESULTADOS:");
        System.out.println("─".repeat(40));
        System.out.printf("Promedio general: %.2f\n", promedio);
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);
        System.out.println("Calificación máxima: " + maxima);
        System.out.println("Calificación mínima: " + minima);
        System.out.println();
        
        System.out.println("Estudiantes sobre el promedio:");
        for (int i = 0; i < calificaciones.length; i++) {
            if (calificaciones[i] > promedio) {
                System.out.printf("  Estudiante %d: %d puntos\n", i + 1, calificaciones[i]);
            }
        }
        
        // EXPLICACIÓN DEL ALGORITMO:
        System.out.println();
        System.out.println("EXPLICACIÓN:");
        System.out.println("1. Recorremos el arreglo sumando todas las calificaciones");
        System.out.println("2. Dividimos la suma entre el número de elementos para obtener el promedio");
        System.out.println("3. Usamos un contador para aprobados y reprobados con condicional");
        System.out.println("4. Comparamos cada elemento para encontrar máximo y mínimo");
        System.out.println("5. Comparamos cada calificación contra el promedio");
    }
    
    /**
     * ╔═══════════════════════════════════════════════════════════════════════╗
     * ║                         EJERCICIO 2                                   ║
     * ║                   NÚMEROS PRIMOS                                      ║
     * ╚═══════════════════════════════════════════════════════════════════════╝
     * 
     * ENUNCIADO:
     * Escribe un programa que:
     * 1. Encuentre todos los números primos menores o iguales a un número N
     * 2. Los almacene en una lista
     * 3. Muestre cuántos números primos se encontraron
     * 4. Calcule la suma de todos los números primos encontrados
     * 5. Identifique los primos gemelos (primos que difieren en 2, como 11 y 13)
     * 
     * CONCEPTOS UTILIZADOS:
     * - Listas (ArrayList)
     * - Ciclos for y while
     * - Condicionales if
     * - Método auxiliar para verificar si un número es primo
     */
    private static void ejercicio2_NumerosPrimos() {
        System.out.println("EJERCICIO 2: NÚMEROS PRIMOS");
        System.out.println("─".repeat(60));
        
        int n = 50;
        System.out.println("Buscando números primos hasta: " + n);
        System.out.println();
        
        // SOLUCIÓN:
        
        // Paso 1: Encontrar todos los primos hasta n
        List<Integer> primos = new ArrayList<>();
        
        for (int numero = 2; numero <= n; numero++) {
            if (esPrimo(numero)) {
                primos.add(numero);
            }
        }
        
        // Paso 2: Mostrar los primos encontrados
        System.out.println("Números primos encontrados:");
        System.out.println(primos);
        System.out.println();
        
        // Paso 3: Contar y sumar
        int cantidad = primos.size();
        int suma = 0;
        
        for (int primo : primos) {
            suma += primo;
        }
        
        System.out.println("ESTADÍSTICAS:");
        System.out.println("─".repeat(40));
        System.out.println("Cantidad de primos: " + cantidad);
        System.out.println("Suma de todos los primos: " + suma);
        System.out.printf("Promedio: %.2f\n", (double) suma / cantidad);
        System.out.println();
        
        // Paso 4: Encontrar primos gemelos
        System.out.println("Primos gemelos (diferencia de 2):");
        for (int i = 0; i < primos.size() - 1; i++) {
            if (primos.get(i + 1) - primos.get(i) == 2) {
                System.out.println("  (" + primos.get(i) + ", " + primos.get(i + 1) + ")");
            }
        }
        
        // EXPLICACIÓN DEL ALGORITMO:
        System.out.println();
        System.out.println("EXPLICACIÓN:");
        System.out.println("1. Usamos ArrayList porque no sabemos cuántos primos habrá");
        System.out.println("2. Verificamos cada número desde 2 hasta n");
        System.out.println("3. Si es primo, lo agregamos a la lista con add()");
        System.out.println("4. Recorremos la lista para calcular suma y estadísticas");
        System.out.println("5. Comparamos elementos consecutivos para encontrar gemelos");
    }
    
    /**
     * Método auxiliar para verificar si un número es primo
     */
    private static boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        
        // Optimización: solo verificamos hasta la raíz cuadrada
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;  // Encontramos un divisor, no es primo
            }
        }
        
        return true;  // No encontramos divisores, es primo
    }
    
    /**
     * ╔═══════════════════════════════════════════════════════════════════════╗
     * ║                         EJERCICIO 3                                   ║
     * ║                   INVERTIR ARREGLO                                    ║
     * ╚═══════════════════════════════════════════════════════════════════════╝
     * 
     * ENUNCIADO:
     * Escribe un programa que:
     * 1. Tome un arreglo de números enteros
     * 2. Lo invierta SIN usar un arreglo auxiliar
     * 3. Muestre el arreglo antes y después de invertirlo
     * 4. Verifique si el arreglo original era un palíndromo
     * 5. Cuente cuántos intercambios se realizaron
     * 
     * CONCEPTOS UTILIZADOS:
     * - Arreglos
     * - Ciclo for
     * - Algoritmo de inversión in-place
     * - Condicionales
     * - Variables auxiliares
     */
    private static void ejercicio3_InvertirArreglo() {
        System.out.println("EJERCICIO 3: INVERTIR ARREGLO");
        System.out.println("─".repeat(60));
        
        // Datos de entrada
        int[] numeros = {10, 20, 30, 40, 50, 60, 70};
        int[] palindromo = {1, 2, 3, 2, 1};
        
        System.out.println("EJEMPLO 1 - Arreglo normal:");
        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        
        // SOLUCIÓN:
        
        // Verificar si es palíndromo ANTES de invertir
        boolean esPalindromo = verificarPalindromo(numeros);
        
        // Invertir el arreglo
        int intercambios = invertirArreglo(numeros);
        
        System.out.println("Arreglo invertido: " + Arrays.toString(numeros));
        System.out.println("Intercambios realizados: " + intercambios);
        System.out.println("¿Era palíndromo?: " + esPalindromo);
        System.out.println();
        
        // Ejemplo 2: Palíndromo
        System.out.println("EJEMPLO 2 - Arreglo palíndromo:");
        System.out.println("Arreglo original: " + Arrays.toString(palindromo));
        
        boolean esPalindromo2 = verificarPalindromo(palindromo);
        int intercambios2 = invertirArreglo(palindromo);
        
        System.out.println("Arreglo invertido: " + Arrays.toString(palindromo));
        System.out.println("Intercambios realizados: " + intercambios2);
        System.out.println("¿Era palíndromo?: " + esPalindromo2);
        
        // EXPLICACIÓN DEL ALGORITMO:
        System.out.println();
        System.out.println("EXPLICACIÓN:");
        System.out.println("1. Usamos dos índices: inicio y fin del arreglo");
        System.out.println("2. Intercambiamos elementos en las posiciones inicio y fin");
        System.out.println("3. Movemos inicio hacia adelante y fin hacia atrás");
        System.out.println("4. Repetimos hasta que los índices se crucen");
        System.out.println("5. No necesitamos espacio adicional (in-place)");
        System.out.println("6. Complejidad: O(n/2) = O(n)");
    }
    
    /**
     * Invierte un arreglo in-place (sin usar arreglo auxiliar)
     * Retorna el número de intercambios realizados
     */
    private static int invertirArreglo(int[] arr) {
        int intercambios = 0;
        int inicio = 0;
        int fin = arr.length - 1;
        
        while (inicio < fin) {
            // Intercambiar elementos
            int temp = arr[inicio];
            arr[inicio] = arr[fin];
            arr[fin] = temp;
            
            intercambios++;
            inicio++;
            fin--;
        }
        
        return intercambios;
    }
    
    /**
     * Verifica si un arreglo es palíndromo
     */
    private static boolean verificarPalindromo(int[] arr) {
        int inicio = 0;
        int fin = arr.length - 1;
        
        while (inicio < fin) {
            if (arr[inicio] != arr[fin]) {
                return false;
            }
            inicio++;
            fin--;
        }
        
        return true;
    }
    
    /**
     * ╔═══════════════════════════════════════════════════════════════════════╗
     * ║                         EJERCICIO 4                                   ║
     * ║              GESTIÓN DE ESTUDIANTES                                   ║
     * ╚═══════════════════════════════════════════════════════════════════════╝
     * 
     * ENUNCIADO:
     * Crea un sistema de gestión de estudiantes que:
     * 1. Almacene nombres de estudiantes y sus calificaciones en listas paralelas
     * 2. Permita buscar un estudiante por nombre
     * 3. Muestre el ranking de estudiantes ordenados por calificación
     * 4. Identifique al estudiante con mejor y peor desempeño
     * 5. Calcule estadísticas por rangos de calificación (A, B, C, D, F)
     * 
     * CONCEPTOS UTILIZADOS:
     * - Listas ArrayList
     * - Ciclos for y for-each
     * - Condicionales if-else y switch
     * - Búsqueda lineal
     * - Algoritmo de ordenamiento (burbuja)
     */
    private static void ejercicio4_GestionEstudiantes() {
        System.out.println("EJERCICIO 4: GESTIÓN DE ESTUDIANTES");
        System.out.println("─".repeat(60));
        
        // Datos de entrada: listas paralelas
        List<String> nombres = new ArrayList<>();
        List<Integer> calificaciones = new ArrayList<>();
        
        // Agregar estudiantes
        agregarEstudiante(nombres, calificaciones, "Ana García", 95);
        agregarEstudiante(nombres, calificaciones, "Carlos López", 78);
        agregarEstudiante(nombres, calificaciones, "María Rodríguez", 88);
        agregarEstudiante(nombres, calificaciones, "Pedro Martínez", 65);
        agregarEstudiante(nombres, calificaciones, "Laura Sánchez", 92);
        agregarEstudiante(nombres, calificaciones, "Diego Torres", 45);
        agregarEstudiante(nombres, calificaciones, "Sofia Ramírez", 83);
        
        System.out.println("Total de estudiantes: " + nombres.size());
        System.out.println();
        
        // SOLUCIÓN:
        
        // Paso 1: Mostrar todos los estudiantes
        System.out.println("LISTA DE ESTUDIANTES:");
        System.out.println("─".repeat(40));
        mostrarEstudiantes(nombres, calificaciones);
        System.out.println();
        
        // Paso 2: Buscar un estudiante
        String nombreBuscar = "María Rodríguez";
        System.out.println("Buscando: " + nombreBuscar);
        int indice = buscarEstudiante(nombres, nombreBuscar);
        
        if (indice != -1) {
            System.out.println("✓ Encontrado: " + nombres.get(indice) + 
                             " - Calificación: " + calificaciones.get(indice));
        } else {
            System.out.println("✗ No encontrado");
        }
        System.out.println();
        
        // Paso 3: Encontrar mejor y peor estudiante
        int indiceMejor = 0;
        int indicePeor = 0;
        
        for (int i = 1; i < calificaciones.size(); i++) {
            if (calificaciones.get(i) > calificaciones.get(indiceMejor)) {
                indiceMejor = i;
            }
            if (calificaciones.get(i) < calificaciones.get(indicePeor)) {
                indicePeor = i;
            }
        }
        
        System.out.println("MEJOR ESTUDIANTE:");
        System.out.println("  " + nombres.get(indiceMejor) + ": " + 
                         calificaciones.get(indiceMejor) + " puntos");
        System.out.println();
        
        System.out.println("ESTUDIANTE CON MÁS DIFICULTADES:");
        System.out.println("  " + nombres.get(indicePeor) + ": " + 
                         calificaciones.get(indicePeor) + " puntos");
        System.out.println();
        
        // Paso 4: Estadísticas por letra (A, B, C, D, F)
        System.out.println("DISTRIBUCIÓN POR CALIFICACIÓN:");
        System.out.println("─".repeat(40));
        mostrarDistribucion(calificaciones);
        System.out.println();
        
        // Paso 5: Ranking ordenado
        System.out.println("RANKING DE ESTUDIANTES:");
        System.out.println("─".repeat(40));
        mostrarRanking(nombres, calificaciones);
        
        // EXPLICACIÓN DEL ALGORITMO:
        System.out.println();
        System.out.println("EXPLICACIÓN:");
        System.out.println("1. Usamos listas paralelas: una para nombres, otra para calificaciones");
        System.out.println("2. El índice i relaciona nombres.get(i) con calificaciones.get(i)");
        System.out.println("3. La búsqueda es lineal O(n) comparando cada nombre");
        System.out.println("4. Encontramos máximo/mínimo en un solo recorrido O(n)");
        System.out.println("5. Para ordenar, creamos copias y usamos ordenamiento burbuja");
    }
    
    private static void agregarEstudiante(List<String> nombres, 
                                         List<Integer> calificaciones, 
                                         String nombre, 
                                         int calificacion) {
        nombres.add(nombre);
        calificaciones.add(calificacion);
    }
    
    private static void mostrarEstudiantes(List<String> nombres, 
                                          List<Integer> calificaciones) {
        for (int i = 0; i < nombres.size(); i++) {
            String letra = obtenerLetra(calificaciones.get(i));
            System.out.printf("%2d. %-20s %3d (%s)\n", 
                            i + 1, nombres.get(i), calificaciones.get(i), letra);
        }
    }
    
    private static int buscarEstudiante(List<String> nombres, String nombre) {
        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }
    
    private static String obtenerLetra(int calificacion) {
        if (calificacion >= 90) return "A";
        else if (calificacion >= 80) return "B";
        else if (calificacion >= 70) return "C";
        else if (calificacion >= 60) return "D";
        else return "F";
    }
    
    private static void mostrarDistribucion(List<Integer> calificaciones) {
        int contadorA = 0, contadorB = 0, contadorC = 0, contadorD = 0, contadorF = 0;
        
        for (int cal : calificaciones) {
            String letra = obtenerLetra(cal);
            switch (letra) {
                case "A" -> contadorA++;
                case "B" -> contadorB++;
                case "C" -> contadorC++;
                case "D" -> contadorD++;
                case "F" -> contadorF++;
            }
        }
        
        System.out.println("A (90-100): " + contadorA + " estudiantes " + 
                         crearBarra(contadorA));
        System.out.println("B (80-89):  " + contadorB + " estudiantes " + 
                         crearBarra(contadorB));
        System.out.println("C (70-79):  " + contadorC + " estudiantes " + 
                         crearBarra(contadorC));
        System.out.println("D (60-69):  " + contadorD + " estudiantes " + 
                         crearBarra(contadorD));
        System.out.println("F (0-59):   " + contadorF + " estudiantes " + 
                         crearBarra(contadorF));
    }
    
    private static String crearBarra(int cantidad) {
        return "█".repeat(cantidad);
    }
    
    private static void mostrarRanking(List<String> nombres, 
                                      List<Integer> calificaciones) {
        // Crear copias para no modificar las listas originales
        List<String> nombresOrdenados = new ArrayList<>(nombres);
        List<Integer> calificacionesOrdenadas = new ArrayList<>(calificaciones);
        
        // Ordenamiento burbuja (descendente)
        for (int i = 0; i < calificacionesOrdenadas.size() - 1; i++) {
            for (int j = 0; j < calificacionesOrdenadas.size() - 1 - i; j++) {
                if (calificacionesOrdenadas.get(j) < calificacionesOrdenadas.get(j + 1)) {
                    // Intercambiar calificaciones
                    int tempCal = calificacionesOrdenadas.get(j);
                    calificacionesOrdenadas.set(j, calificacionesOrdenadas.get(j + 1));
                    calificacionesOrdenadas.set(j + 1, tempCal);
                    
                    // Intercambiar nombres (para mantener la relación)
                    String tempNom = nombresOrdenados.get(j);
                    nombresOrdenados.set(j, nombresOrdenados.get(j + 1));
                    nombresOrdenados.set(j + 1, tempNom);
                }
            }
        }
        
        // Mostrar ranking
        for (int i = 0; i < nombresOrdenados.size(); i++) {
            String medalla = "";
            if (i == 0) medalla = "🥇";
            else if (i == 1) medalla = "🥈";
            else if (i == 2) medalla = "🥉";
            
            System.out.printf("%2d. %-20s %3d puntos %s\n", 
                            i + 1, nombresOrdenados.get(i), 
                            calificacionesOrdenadas.get(i), medalla);
        }
    }
    
    /**
     * ╔═══════════════════════════════════════════════════════════════════════╗
     * ║                         EJERCICIO 5                                   ║
     * ║              ESTADÍSTICAS DE VENTAS                                   ║
     * ╚═══════════════════════════════════════════════════════════════════════╝
     * 
     * ENUNCIADO:
     * Crea un programa que analice las ventas de una tienda:
     * 1. Almacene las ventas de cada día de la semana en un arreglo
     * 2. Calcule el total de ventas de la semana
     * 3. Encuentre el día con más y menos ventas
     * 4. Calcule el promedio diario
     * 5. Identifique qué días estuvieron por encima del promedio
     * 6. Simule una proyección para el próximo mes
     * 7. Detecte tendencias (ventas crecientes, decrecientes o estables)
     * 
     * CONCEPTOS UTILIZADOS:
     * - Arreglos
     * - Ciclos for y while
     * - Condicionales complejas
     * - Operaciones matemáticas
     * - Análisis de datos
     */
    private static void ejercicio5_EstadisticasVentas() {
        System.out.println("EJERCICIO 5: ESTADÍSTICAS DE VENTAS");
        System.out.println("─".repeat(60));
        
        // Datos de entrada: ventas por día de la semana (en miles de pesos)
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        double[] ventas = {1250.50, 1380.75, 1420.30, 1560.90, 1890.45, 2340.80, 2150.60};
        
        System.out.println("VENTAS DE LA SEMANA (en miles de pesos):");
        System.out.println("─".repeat(40));
        
        for (int i = 0; i < dias.length; i++) {
            System.out.printf("%-12s: $%,.2f\n", dias[i], ventas[i]);
        }
        System.out.println();
        
        // SOLUCIÓN:
        
        // Paso 1: Calcular total y promedio
        double totalVentas = 0;
        for (double venta : ventas) {
            totalVentas += venta;
        }
        double promedio = totalVentas / ventas.length;
        
        System.out.println("ANÁLISIS GENERAL:");
        System.out.println("─".repeat(40));
        System.out.printf("Total de ventas: $%,.2f\n", totalVentas);
        System.out.printf("Promedio diario: $%,.2f\n", promedio);
        System.out.println();
        
        // Paso 2: Encontrar mejor y peor día
        int indiceMejor = 0;
        int indicePeor = 0;
        
        for (int i = 1; i < ventas.length; i++) {
            if (ventas[i] > ventas[indiceMejor]) {
                indiceMejor = i;
            }
            if (ventas[i] < ventas[indicePeor]) {
                indicePeor = i;
            }
        }
        
        System.out.println("DÍAS DESTACADOS:");
        System.out.println("─".repeat(40));
        System.out.printf("Mejor día: %s con $%,.2f\n", 
                        dias[indiceMejor], ventas[indiceMejor]);
        System.out.printf("Peor día: %s con $%,.2f\n", 
                        dias[indicePeor], ventas[indicePeor]);
        
        double diferencia = ventas[indiceMejor] - ventas[indicePeor];
        double porcentajeDiferencia = (diferencia / ventas[indicePeor]) * 100;
        System.out.printf("Diferencia: $%,.2f (%.1f%% más)\n", 
                        diferencia, porcentajeDiferencia);
        System.out.println();
        
        // Paso 3: Días sobre el promedio
        System.out.println("DÍAS SOBRE EL PROMEDIO:");
        System.out.println("─".repeat(40));
        int diasSobrePromedio = 0;
        
        for (int i = 0; i < ventas.length; i++) {
            if (ventas[i] > promedio) {
                diasSobrePromedio++;
                double diferenciaProm = ventas[i] - promedio;
                System.out.printf("%-12s: $%,.2f (+$%,.2f)\n", 
                                dias[i], ventas[i], diferenciaProm);
            }
        }
        System.out.println("Total: " + diasSobrePromedio + " días");
        System.out.println();
        
        // Paso 4: Detectar tendencia
        System.out.println("ANÁLISIS DE TENDENCIA:");
        System.out.println("─".repeat(40));
        
        int diasCrecimiento = 0;
        int diasDecrecimiento = 0;
        
        for (int i = 1; i < ventas.length; i++) {
            if (ventas[i] > ventas[i - 1]) {
                diasCrecimiento++;
            } else if (ventas[i] < ventas[i - 1]) {
                diasDecrecimiento++;
            }
        }
        
        String tendencia;
        if (diasCrecimiento > diasDecrecimiento) {
            tendencia = "CRECIENTE ↗";
        } else if (diasDecrecimiento > diasCrecimiento) {
            tendencia = "DECRECIENTE ↘";
        } else {
            tendencia = "ESTABLE →";
        }
        
        System.out.println("Días con crecimiento: " + diasCrecimiento);
        System.out.println("Días con decrecimiento: " + diasDecrecimiento);
        System.out.println("Tendencia general: " + tendencia);
        System.out.println();
        
        // Paso 5: Proyección para el próximo mes
        System.out.println("PROYECCIÓN PARA EL PRÓXIMO MES:");
        System.out.println("─".repeat(40));
        
        // Calcular tasa de crecimiento promedio
        double tasaCrecimiento = 0;
        int comparaciones = 0;
        
        for (int i = 1; i < ventas.length; i++) {
            double variacion = ((ventas[i] - ventas[i - 1]) / ventas[i - 1]) * 100;
            tasaCrecimiento += variacion;
            comparaciones++;
        }
        tasaCrecimiento = tasaCrecimiento / comparaciones;
        
        double proyeccionMensual = totalVentas * 4; // 4 semanas aproximadas
        double proyeccionConCrecimiento = proyeccionMensual * (1 + tasaCrecimiento / 100);
        
        System.out.printf("Tasa de crecimiento promedio: %.2f%%\n", tasaCrecimiento);
        System.out.printf("Proyección base (4 semanas): $%,.2f\n", proyeccionMensual);
        System.out.printf("Proyección con tendencia: $%,.2f\n", proyeccionConCrecimiento);
        System.out.println();
        
        // Paso 6: Gráfico de barras simple
        System.out.println("GRÁFICO DE VENTAS:");
        System.out.println("─".repeat(40));
        
        double escala = 100; // Cada █ representa $100
        
        for (int i = 0; i < dias.length; i++) {
            int barras = (int) (ventas[i] / escala);
            String grafico = "█".repeat(barras);
            System.out.printf("%-12s: %s $%,.2f\n", dias[i], grafico, ventas[i]);
        }
        
        // EXPLICACIÓN DEL ALGORITMO:
        System.out.println();
        System.out.println("EXPLICACIÓN:");
        System.out.println("1. Usamos un arreglo paralelo para días y ventas");
        System.out.println("2. Recorremos el arreglo para calcular suma y encontrar extremos");
        System.out.println("3. Comparamos cada venta con el promedio usando condicionales");
        System.out.println("4. Analizamos tendencias comparando elementos consecutivos");
        System.out.println("5. Calculamos proyecciones usando porcentajes y multiplicación");
        System.out.println("6. Visualizamos con caracteres usando repetición de strings");
    }
}
