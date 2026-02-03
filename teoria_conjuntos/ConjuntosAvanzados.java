import java.util.*;
import java.util.stream.Collectors;

/**
 * Operaciones Avanzadas de Conjuntos
 * Incluye análisis de rendimiento y casos de uso complejos
 */
public class ConjuntosAvanzados {
    
    public static void main(String[] args) {
        System.out.println("=== OPERACIONES AVANZADAS DE CONJUNTOS ===\n");
        
        analizarRendimiento();
        productosCartesianos();
        particionesYCombinaciones();
        relacionesEntreConjuntos();
        aplicacionesMatematicas();
    }
    
    /**
     * Análisis de rendimiento de diferentes implementaciones
     */
    private static void analizarRendimiento() {
        System.out.println("--- ANÁLISIS DE RENDIMIENTO ---\n");
        
        int n = 10000;
        
        // HashSet - O(1) promedio para búsqueda
        long inicio = System.nanoTime();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            hashSet.add(i);
        }
        boolean encontrado = hashSet.contains(n - 1);
        long hashTime = System.nanoTime() - inicio;
        
        // TreeSet - O(log n) para búsqueda
        inicio = System.nanoTime();
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            treeSet.add(i);
        }
        encontrado = treeSet.contains(n - 1);
        long treeTime = System.nanoTime() - inicio;
        
        // LinkedHashSet - O(1) con orden de inserción
        inicio = System.nanoTime();
        Set<Integer> linkedSet = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            linkedSet.add(i);
        }
        encontrado = linkedSet.contains(n - 1);
        long linkedTime = System.nanoTime() - inicio;
        
        System.out.println("Tiempo para " + n + " elementos:");
        System.out.println("HashSet: " + hashTime / 1_000_000.0 + " ms");
        System.out.println("TreeSet: " + treeTime / 1_000_000.0 + " ms");
        System.out.println("LinkedHashSet: " + linkedTime / 1_000_000.0 + " ms");
        
        System.out.println("\nCaracterísticas:");
        System.out.println("HashSet: Más rápido, sin orden");
        System.out.println("TreeSet: Ordenado, búsqueda O(log n)");
        System.out.println("LinkedHashSet: Mantiene orden de inserción");
        System.out.println();
    }
    
    /**
     * Producto Cartesiano: A × B
     * Conjunto de todos los pares ordenados (a, b) donde a ∈ A y b ∈ B
     */
    private static void productosCartesianos() {
        System.out.println("--- PRODUCTO CARTESIANO ---\n");
        
        Set<String> colores = Set.of("Rojo", "Azul");
        Set<Integer> tallas = Set.of(S, M, L);
        
        Set<Par<String, Integer>> productoCartesiano = new HashSet<>();
        for (String color : colores) {
            for (Integer talla : tallas) {
                productoCartesiano.add(new Par<>(color, talla));
            }
        }
        
        System.out.println("Colores: " + colores);
        System.out.println("Tallas: " + tallas);
        System.out.println("Producto Cartesiano (Colores × Tallas):");
        productoCartesiano.forEach(System.out::println);
        System.out.println("Cardinalidad: |A × B| = |A| × |B| = " + 
                         colores.size() + " × " + tallas.size() + " = " + 
                         productoCartesiano.size());
        
        // Usando Streams
        Set<Par<String, Integer>> productoStream = colores.stream()
            .flatMap(color -> tallas.stream().map(talla -> new Par<>(color, talla)))
            .collect(Collectors.toSet());
        
        System.out.println("\nProducto usando Streams: " + productoStream.size() + " elementos");
        System.out.println();
    }
    
    /**
     * Particiones y Combinaciones
     */
    private static void particionesYCombinaciones() {
        System.out.println("--- PARTICIONES Y COMBINACIONES ---\n");
        
        // Generar combinaciones de tamaño k
        Set<Integer> conjunto = Set.of(1, 2, 3, 4);
        int k = 2;
        
        Set<Set<Integer>> combinaciones = generarCombinaciones(conjunto, k);
        System.out.println("Conjunto: " + conjunto);
        System.out.println("Combinaciones de tamaño " + k + ":");
        combinaciones.forEach(System.out::println);
        System.out.println("Total: C(" + conjunto.size() + ", " + k + ") = " + 
                         combinaciones.size() + " combinaciones");
        
        // Particiones de un conjunto
        System.out.println("\nParticiones de {1, 2, 3}:");
        Set<Integer> pequeno = Set.of(1, 2, 3);
        Set<Set<Set<Integer>>> particiones = generarParticiones(pequeno);
        particiones.forEach(particion -> {
            System.out.println("  " + particion);
        });
        
        System.out.println();
    }
    
    /**
     * Relaciones entre Conjuntos
     */
    private static void relacionesEntreConjuntos() {
        System.out.println("--- RELACIONES ENTRE CONJUNTOS ---\n");
        
        Set<Integer> A = Set.of(1, 2, 3, 4);
        Set<Integer> B = Set.of(3, 4, 5, 6);
        Set<Integer> C = Set.of(1, 2);
        Set<Integer> D = Set.of(7, 8, 9);
        
        System.out.println("A = " + A);
        System.out.println("B = " + B);
        System.out.println("C = " + C);
        System.out.println("D = " + D);
        System.out.println();
        
        // Cardinalidad
        System.out.println("Cardinalidades:");
        System.out.println("|A| = " + A.size());
        System.out.println("|B| = " + B.size());
        
        // Relación de inclusión
        System.out.println("\nRelaciones de inclusión:");
        System.out.println("C ⊆ A: " + A.containsAll(C) + " (C es subconjunto de A)");
        System.out.println("A ⊆ C: " + C.containsAll(A) + " (A es subconjunto de C)");
        System.out.println("C ⊂ A (subconjunto propio): " + 
                         (A.containsAll(C) && !A.equals(C)));
        
        // Conjuntos disjuntos
        System.out.println("\nConjuntos disjuntos:");
        System.out.println("A ∩ D = ∅: " + Collections.disjoint(A, D));
        System.out.println("A ∩ B = ∅: " + Collections.disjoint(A, B));
        
        // Leyes de De Morgan
        Set<Integer> U = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        demostrarLeyesDeMorgan(U, A, B);
        
        System.out.println();
    }
    
    /**
     * Aplicaciones Matemáticas
     */
    private static void aplicacionesMatematicas() {
        System.out.println("--- APLICACIONES MATEMÁTICAS ---\n");
        
        // 1. Primos menores que 30
        Set<Integer> primos = Set.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        
        // 2. Múltiplos de 3 menores que 30
        Set<Integer> multiplosDe3 = new HashSet<>();
        for (int i = 3; i < 30; i += 3) {
            multiplosDe3.add(i);
        }
        
        System.out.println("Primos < 30: " + primos);
        System.out.println("Múltiplos de 3 < 30: " + multiplosDe3);
        
        // Intersección: números que son primos Y múltiplos de 3
        Set<Integer> primosMultiplos3 = new HashSet<>(primos);
        primosMultiplos3.retainAll(multiplosDe3);
        System.out.println("Primos que son múltiplos de 3: " + primosMultiplos3);
        
        // Teorema: El conjunto de números de Fibonacci
        Set<Integer> fibonacci = generarFibonacci(100);
        System.out.println("\nFibonacci < 100: " + fibonacci);
        
        // Números perfectos
        Set<Integer> perfectos = encontrarNumerosPerfectos(1000);
        System.out.println("Números perfectos < 1000: " + perfectos);
        
        System.out.println();
    }
    
    /**
     * Demuestra las Leyes de De Morgan
     * 1. (A ∪ B)' = A' ∩ B'
     * 2. (A ∩ B)' = A' ∪ B'
     */
    private static void demostrarLeyesDeMorgan(Set<Integer> U, Set<Integer> A, Set<Integer> B) {
        System.out.println("\nLeyes de De Morgan:");
        System.out.println("Conjunto Universal U = " + U);
        
        // Complementos
        Set<Integer> complementoA = new HashSet<>(U);
        complementoA.removeAll(A);
        
        Set<Integer> complementoB = new HashSet<>(U);
        complementoB.removeAll(B);
        
        // Primera Ley: (A ∪ B)' = A' ∩ B'
        Set<Integer> unionAB = new HashSet<>(A);
        unionAB.addAll(B);
        Set<Integer> complementoUnion = new HashSet<>(U);
        complementoUnion.removeAll(unionAB);
        
        Set<Integer> interseccionComplementos = new HashSet<>(complementoA);
        interseccionComplementos.retainAll(complementoB);
        
        System.out.println("Primera Ley: (A ∪ B)' = A' ∩ B'");
        System.out.println("  (A ∪ B)' = " + complementoUnion);
        System.out.println("  A' ∩ B' = " + interseccionComplementos);
        System.out.println("  ¿Son iguales? " + complementoUnion.equals(interseccionComplementos));
        
        // Segunda Ley: (A ∩ B)' = A' ∪ B'
        Set<Integer> interseccionAB = new HashSet<>(A);
        interseccionAB.retainAll(B);
        Set<Integer> complementoInterseccion = new HashSet<>(U);
        complementoInterseccion.removeAll(interseccionAB);
        
        Set<Integer> unionComplementos = new HashSet<>(complementoA);
        unionComplementos.addAll(complementoB);
        
        System.out.println("\nSegunda Ley: (A ∩ B)' = A' ∪ B'");
        System.out.println("  (A ∩ B)' = " + complementoInterseccion);
        System.out.println("  A' ∪ B' = " + unionComplementos);
        System.out.println("  ¿Son iguales? " + complementoInterseccion.equals(unionComplementos));
    }
    
    /**
     * Genera combinaciones de tamaño k de un conjunto
     */
    private static <T> Set<Set<T>> generarCombinaciones(Set<T> conjunto, int k) {
        Set<Set<T>> combinaciones = new HashSet<>();
        if (k == 0) {
            combinaciones.add(new HashSet<>());
            return combinaciones;
        }
        if (k > conjunto.size()) {
            return combinaciones;
        }
        
        List<T> lista = new ArrayList<>(conjunto);
        generarCombinacionesRecursivo(lista, k, 0, new HashSet<>(), combinaciones);
        return combinaciones;
    }
    
    private static <T> void generarCombinacionesRecursivo(
            List<T> lista, int k, int inicio, Set<T> actual, Set<Set<T>> resultado) {
        if (actual.size() == k) {
            resultado.add(new HashSet<>(actual));
            return;
        }
        for (int i = inicio; i < lista.size(); i++) {
            actual.add(lista.get(i));
            generarCombinacionesRecursivo(lista, k, i + 1, actual, resultado);
            actual.remove(lista.get(i));
        }
    }
    
    /**
     * Genera todas las particiones de un conjunto
     */
    private static <T> Set<Set<Set<T>>> generarParticiones(Set<T> conjunto) {
        if (conjunto.isEmpty()) {
            Set<Set<Set<T>>> resultado = new HashSet<>();
            resultado.add(new HashSet<>());
            return resultado;
        }
        
        Set<Set<Set<T>>> particiones = new HashSet<>();
        List<T> lista = new ArrayList<>(conjunto);
        T primero = lista.get(0);
        Set<T> resto = new HashSet<>(lista.subList(1, lista.size()));
        
        for (Set<Set<T>> particionResto : generarParticiones(resto)) {
            // Agregar primero a cada bloque existente
            for (Set<T> bloque : particionResto) {
                Set<Set<T>> nuevaParticion = new HashSet<>();
                for (Set<T> b : particionResto) {
                    if (b.equals(bloque)) {
                        Set<T> nuevoBloque = new HashSet<>(b);
                        nuevoBloque.add(primero);
                        nuevaParticion.add(nuevoBloque);
                    } else {
                        nuevaParticion.add(new HashSet<>(b));
                    }
                }
                particiones.add(nuevaParticion);
            }
            
            // Agregar primero como nuevo bloque
            Set<Set<T>> nuevaParticion = new HashSet<>(particionResto);
            nuevaParticion.add(Set.of(primero));
            particiones.add(nuevaParticion);
        }
        
        return particiones;
    }
    
    /**
     * Genera números de Fibonacci menores que max
     */
    private static Set<Integer> generarFibonacci(int max) {
        Set<Integer> fibonacci = new TreeSet<>();
        int a = 0, b = 1;
        while (a < max) {
            fibonacci.add(a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        return fibonacci;
    }
    
    /**
     * Encuentra números perfectos (suma de divisores = número)
     */
    private static Set<Integer> encontrarNumerosPerfectos(int max) {
        Set<Integer> perfectos = new TreeSet<>();
        for (int n = 2; n < max; n++) {
            int sumaDivisores = 0;
            for (int i = 1; i <= n / 2; i++) {
                if (n % i == 0) {
                    sumaDivisores += i;
                }
            }
            if (sumaDivisores == n) {
                perfectos.add(n);
            }
        }
        return perfectos;
    }
    
    // Constantes para tallas
    private static final Integer S = 1;
    private static final Integer M = 2;
    private static final Integer L = 3;
    
    /**
     * Clase auxiliar para representar pares ordenados
     */
    static class Par<A, B> {
        private final A primero;
        private final B segundo;
        
        public Par(A primero, B segundo) {
            this.primero = primero;
            this.segundo = segundo;
        }
        
        @Override
        public String toString() {
            return "(" + primero + ", " + segundo + ")";
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Par)) return false;
            Par<?, ?> par = (Par<?, ?>) o;
            return Objects.equals(primero, par.primero) && 
                   Objects.equals(segundo, par.segundo);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(primero, segundo);
        }
    }
}
