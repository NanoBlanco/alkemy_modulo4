import java.util.*;
import java.util.stream.Collectors;

/**
 * PROGRAMA DE PRUEBAS Y DEMOSTRACIÓN INTERACTIVA
 * 
 * Este programa permite experimentar con diferentes operaciones de conjuntos
 * y ver los resultados en tiempo real.
 */
public class DemoInteractiva {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Set<Integer>> conjuntos = new HashMap<>();
    
    public static void main(String[] args) {
        inicializarConjuntosEjemplo();
        
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   DEMOSTRACIÓN INTERACTIVA DE TEORÍA DE CONJUNTOS    ║");
        System.out.println("║              Java 17 - Tutorial Completo              ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1 -> crearConjunto();
                case 2 -> mostrarConjuntos();
                case 3 -> realizarUnion();
                case 4 -> realizarInterseccion();
                case 5 -> realizarDiferencia();
                case 6 -> realizarDiferenciaSimetrica();
                case 7 -> verificarSubconjunto();
                case 8 -> calcularComplemento();
                case 9 -> conjuntoPotencia();
                case 10 -> productoCartesiano();
                case 11 -> compararImplementaciones();
                case 12 -> ejemplosVisuales();
                case 0 -> {
                    continuar = false;
                    System.out.println("\n¡Gracias por usar el programa!");
                }
                default -> System.out.println("\nOpción inválida. Intenta de nuevo.");
            }
            
            if (continuar && opcion != 0) {
                System.out.println("\nPresiona Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MENÚ PRINCIPAL");
        System.out.println("=".repeat(60));
        System.out.println("Conjuntos disponibles: " + conjuntos.keySet());
        System.out.println();
        System.out.println("GESTIÓN DE CONJUNTOS:");
        System.out.println("  1. Crear nuevo conjunto");
        System.out.println("  2. Mostrar todos los conjuntos");
        System.out.println();
        System.out.println("OPERACIONES BÁSICAS:");
        System.out.println("  3. Unión (A ∪ B)");
        System.out.println("  4. Intersección (A ∩ B)");
        System.out.println("  5. Diferencia (A - B)");
        System.out.println("  6. Diferencia Simétrica (A Δ B)");
        System.out.println();
        System.out.println("OPERACIONES AVANZADAS:");
        System.out.println("  7. Verificar Subconjunto (A ⊆ B)");
        System.out.println("  8. Calcular Complemento (A')");
        System.out.println("  9. Conjunto Potencia P(A)");
        System.out.println(" 10. Producto Cartesiano (A × B)");
        System.out.println();
        System.out.println("COMPARACIONES Y EJEMPLOS:");
        System.out.println(" 11. Comparar implementaciones (HashSet vs TreeSet vs LinkedHashSet)");
        System.out.println(" 12. Ejemplos visuales con diagramas");
        System.out.println();
        System.out.println("  0. Salir");
        System.out.println("=".repeat(60));
        System.out.print("Selecciona una opción: ");
    }
    
    private static int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void inicializarConjuntosEjemplo() {
        conjuntos.put("A", new HashSet<>(Set.of(1, 2, 3, 4, 5)));
        conjuntos.put("B", new HashSet<>(Set.of(4, 5, 6, 7, 8)));
        conjuntos.put("C", new HashSet<>(Set.of(1, 2, 3)));
        conjuntos.put("U", new HashSet<>(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }
    
    private static void crearConjunto() {
        System.out.println("\n--- CREAR NUEVO CONJUNTO ---");
        System.out.print("Nombre del conjunto: ");
        String nombre = scanner.nextLine().trim().toUpperCase();
        
        if (conjuntos.containsKey(nombre)) {
            System.out.print("El conjunto '" + nombre + "' ya existe. ¿Sobrescribir? (s/n): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
                return;
            }
        }
        
        System.out.print("Ingresa los elementos separados por comas (ej: 1,2,3,4): ");
        String input = scanner.nextLine().trim();
        
        Set<Integer> nuevoConjunto = new HashSet<>();
        try {
            String[] elementos = input.split(",");
            for (String elem : elementos) {
                nuevoConjunto.add(Integer.parseInt(elem.trim()));
            }
            conjuntos.put(nombre, nuevoConjunto);
            System.out.println("\n✓ Conjunto '" + nombre + "' creado: " + nuevoConjunto);
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Error: Ingresa solo números separados por comas");
        }
    }
    
    private static void mostrarConjuntos() {
        System.out.println("\n--- CONJUNTOS ACTUALES ---");
        if (conjuntos.isEmpty()) {
            System.out.println("No hay conjuntos definidos.");
            return;
        }
        
        conjuntos.forEach((nombre, conjunto) -> {
            System.out.printf("%-5s = %-30s |%s| = %d\n", 
                nombre, conjunto, nombre, conjunto.size());
        });
    }
    
    private static void realizarUnion() {
        System.out.println("\n--- UNIÓN (A ∪ B) ---");
        Set<Integer> A = seleccionarConjunto("Primer conjunto (A)");
        if (A == null) return;
        Set<Integer> B = seleccionarConjunto("Segundo conjunto (B)");
        if (B == null) return;
        
        Set<Integer> union = new HashSet<>(A);
        union.addAll(B);
        
        System.out.println("\nA = " + A);
        System.out.println("B = " + B);
        System.out.println("─".repeat(40));
        System.out.println("A ∪ B = " + union);
        System.out.println("\nExplicación: La unión contiene todos los elementos de A y B.");
        System.out.println("Cardinalidad: |A ∪ B| = " + union.size());
        
        visualizarOperacion(A, B, union, "UNION");
    }
    
    private static void realizarInterseccion() {
        System.out.println("\n--- INTERSECCIÓN (A ∩ B) ---");
        Set<Integer> A = seleccionarConjunto("Primer conjunto (A)");
        if (A == null) return;
        Set<Integer> B = seleccionarConjunto("Segundo conjunto (B)");
        if (B == null) return;
        
        Set<Integer> interseccion = new HashSet<>(A);
        interseccion.retainAll(B);
        
        System.out.println("\nA = " + A);
        System.out.println("B = " + B);
        System.out.println("─".repeat(40));
        System.out.println("A ∩ B = " + interseccion);
        System.out.println("\nExplicación: La intersección contiene solo elementos comunes.");
        System.out.println("Cardinalidad: |A ∩ B| = " + interseccion.size());
        
        if (interseccion.isEmpty()) {
            System.out.println("⚠ Los conjuntos son DISJUNTOS (no tienen elementos comunes)");
        }
        
        visualizarOperacion(A, B, interseccion, "INTERSECCION");
    }
    
    private static void realizarDiferencia() {
        System.out.println("\n--- DIFERENCIA (A - B) ---");
        Set<Integer> A = seleccionarConjunto("Conjunto A");
        if (A == null) return;
        Set<Integer> B = seleccionarConjunto("Conjunto B");
        if (B == null) return;
        
        Set<Integer> diferencia = new HashSet<>(A);
        diferencia.removeAll(B);
        
        System.out.println("\nA = " + A);
        System.out.println("B = " + B);
        System.out.println("─".repeat(40));
        System.out.println("A - B = " + diferencia);
        System.out.println("\nExplicación: Elementos que están en A pero NO en B.");
        System.out.println("Cardinalidad: |A - B| = " + diferencia.size());
        
        visualizarOperacion(A, B, diferencia, "DIFERENCIA");
    }
    
    private static void realizarDiferenciaSimetrica() {
        System.out.println("\n--- DIFERENCIA SIMÉTRICA (A Δ B) ---");
        Set<Integer> A = seleccionarConjunto("Primer conjunto (A)");
        if (A == null) return;
        Set<Integer> B = seleccionarConjunto("Segundo conjunto (B)");
        if (B == null) return;
        
        Set<Integer> simetrica = new HashSet<>(A);
        simetrica.addAll(B);
        Set<Integer> interseccion = new HashSet<>(A);
        interseccion.retainAll(B);
        simetrica.removeAll(interseccion);
        
        System.out.println("\nA = " + A);
        System.out.println("B = " + B);
        System.out.println("─".repeat(40));
        System.out.println("A Δ B = " + simetrica);
        System.out.println("\nExplicación: Elementos en A o B, pero NO en ambos.");
        System.out.println("También se puede ver como: (A - B) ∪ (B - A)");
        System.out.println("Cardinalidad: |A Δ B| = " + simetrica.size());
        
        visualizarOperacion(A, B, simetrica, "SIMETRICA");
    }
    
    private static void verificarSubconjunto() {
        System.out.println("\n--- VERIFICAR SUBCONJUNTO (A ⊆ B) ---");
        Set<Integer> A = seleccionarConjunto("Conjunto A");
        if (A == null) return;
        Set<Integer> B = seleccionarConjunto("Conjunto B");
        if (B == null) return;
        
        boolean esSubconjunto = B.containsAll(A);
        boolean esSubconjuntoPropio = esSubconjunto && !A.equals(B);
        
        System.out.println("\nA = " + A);
        System.out.println("B = " + B);
        System.out.println("─".repeat(40));
        System.out.println("A ⊆ B (subconjunto): " + esSubconjunto);
        System.out.println("A ⊂ B (subconjunto propio): " + esSubconjuntoPropio);
        System.out.println("A = B (iguales): " + A.equals(B));
        
        if (esSubconjunto) {
            System.out.println("\n✓ A es subconjunto de B");
            System.out.println("  Todos los elementos de A están en B");
        } else {
            Set<Integer> noEnB = new HashSet<>(A);
            noEnB.removeAll(B);
            System.out.println("\n✗ A NO es subconjunto de B");
            System.out.println("  Elementos de A que no están en B: " + noEnB);
        }
    }
    
    private static void calcularComplemento() {
        System.out.println("\n--- COMPLEMENTO (A') ---");
        Set<Integer> A = seleccionarConjunto("Conjunto A");
        if (A == null) return;
        
        if (!conjuntos.containsKey("U")) {
            System.out.println("\n⚠ No existe conjunto universal 'U'. Créalo primero.");
            return;
        }
        
        Set<Integer> U = conjuntos.get("U");
        Set<Integer> complemento = new HashSet<>(U);
        complemento.removeAll(A);
        
        System.out.println("\nU (Universal) = " + U);
        System.out.println("A = " + A);
        System.out.println("─".repeat(40));
        System.out.println("A' (complemento) = " + complemento);
        System.out.println("\nExplicación: Elementos del universo que NO están en A.");
        System.out.println("Cardinalidad: |A'| = " + complemento.size());
        
        // Verificar propiedades
        Set<Integer> unionConComplemento = new HashSet<>(A);
        unionConComplemento.addAll(complemento);
        System.out.println("\nPropiedades:");
        System.out.println("A ∪ A' = U: " + unionConComplemento.equals(U));
        System.out.println("A ∩ A' = ∅: " + Collections.disjoint(A, complemento));
    }
    
    private static void conjuntoPotencia() {
        System.out.println("\n--- CONJUNTO POTENCIA P(A) ---");
        Set<Integer> A = seleccionarConjunto("Conjunto A");
        if (A == null) return;
        
        if (A.size() > 5) {
            System.out.println("\n⚠ El conjunto tiene más de 5 elementos.");
            System.out.println("  P(A) tendrá 2^" + A.size() + " = " + 
                             Math.pow(2, A.size()) + " elementos.");
            System.out.print("  ¿Continuar? (s/n): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
                return;
            }
        }
        
        Set<Set<Integer>> potencia = generarConjuntoPotencia(A);
        
        System.out.println("\nA = " + A);
        System.out.println("─".repeat(40));
        System.out.println("P(A) = {");
        
        // Agrupar por tamaño para mejor visualización
        Map<Integer, List<Set<Integer>>> porTamano = potencia.stream()
            .collect(Collectors.groupingBy(Set::size));
        
        for (int i = 0; i <= A.size(); i++) {
            if (porTamano.containsKey(i)) {
                System.out.println("  Tamaño " + i + ": " + porTamano.get(i));
            }
        }
        System.out.println("}");
        
        System.out.println("\nExplicación: P(A) contiene TODOS los subconjuntos posibles de A.");
        System.out.println("Cardinalidad: |P(A)| = 2^|A| = 2^" + A.size() + 
                         " = " + potencia.size());
    }
    
    private static void productoCartesiano() {
        System.out.println("\n--- PRODUCTO CARTESIANO (A × B) ---");
        Set<Integer> A = seleccionarConjunto("Conjunto A");
        if (A == null) return;
        Set<Integer> B = seleccionarConjunto("Conjunto B");
        if (B == null) return;
        
        int tamanoProducto = A.size() * B.size();
        if (tamanoProducto > 50) {
            System.out.println("\n⚠ El producto tendrá " + tamanoProducto + " elementos.");
            System.out.print("  ¿Continuar? (s/n): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
                return;
            }
        }
        
        Set<String> producto = new HashSet<>();
        for (Integer a : A) {
            for (Integer b : B) {
                producto.add("(" + a + ", " + b + ")");
            }
        }
        
        System.out.println("\nA = " + A);
        System.out.println("B = " + B);
        System.out.println("─".repeat(40));
        System.out.println("A × B = {");
        
        // Mostrar en filas de 5 elementos
        List<String> lista = new ArrayList<>(producto);
        for (int i = 0; i < lista.size(); i += 5) {
            int fin = Math.min(i + 5, lista.size());
            System.out.println("  " + lista.subList(i, fin));
        }
        System.out.println("}");
        
        System.out.println("\nExplicación: Conjunto de todos los pares ordenados (a, b)");
        System.out.println("  donde a ∈ A y b ∈ B.");
        System.out.println("Cardinalidad: |A × B| = |A| × |B| = " + 
                         A.size() + " × " + B.size() + " = " + producto.size());
    }
    
    private static void compararImplementaciones() {
        System.out.println("\n--- COMPARACIÓN DE IMPLEMENTACIONES ---\n");
        
        int n = 10000;
        System.out.println("Probando con " + n + " elementos...\n");
        
        // HashSet
        long inicio = System.nanoTime();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < n; i++) hashSet.add(i);
        boolean found = hashSet.contains(n - 1);
        long hashTime = System.nanoTime() - inicio;
        
        // LinkedHashSet
        inicio = System.nanoTime();
        Set<Integer> linkedSet = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) linkedSet.add(i);
        found = linkedSet.contains(n - 1);
        long linkedTime = System.nanoTime() - inicio;
        
        // TreeSet
        inicio = System.nanoTime();
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++) treeSet.add(i);
        found = treeSet.contains(n - 1);
        long treeTime = System.nanoTime() - inicio;
        
        System.out.printf("┌─────────────────┬──────────────┬────────────────────┐\n");
        System.out.printf("│ Implementación  │ Tiempo (ms)  │ Características    │\n");
        System.out.printf("├─────────────────┼──────────────┼────────────────────┤\n");
        System.out.printf("│ HashSet         │ %11.3f  │ Rápido, sin orden  │\n", hashTime / 1_000_000.0);
        System.out.printf("│ LinkedHashSet   │ %11.3f  │ Orden inserción    │\n", linkedTime / 1_000_000.0);
        System.out.printf("│ TreeSet         │ %11.3f  │ Siempre ordenado   │\n", treeTime / 1_000_000.0);
        System.out.printf("└─────────────────┴──────────────┴────────────────────┘\n");
        
        System.out.println("\nDemostración de orden:");
        Set<Integer> demo = Set.of(5, 2, 8, 1, 9);
        System.out.println("Elementos insertados: " + demo);
        System.out.println("HashSet:        " + new HashSet<>(demo));
        System.out.println("LinkedHashSet:  " + new LinkedHashSet<>(demo));
        System.out.println("TreeSet:        " + new TreeSet<>(demo));
    }
    
    private static void ejemplosVisuales() {
        System.out.println("\n--- EJEMPLOS VISUALES ---\n");
        
        Set<Integer> A = Set.of(1, 2, 3, 4);
        Set<Integer> B = Set.of(3, 4, 5, 6);
        
        System.out.println("Conjunto A: " + A);
        System.out.println("Conjunto B: " + B);
        System.out.println();
        
        visualizarTodas(A, B);
    }
    
    private static void visualizarTodas(Set<Integer> A, Set<Integer> B) {
        Set<Integer> union = new HashSet<>(A);
        union.addAll(B);
        
        Set<Integer> interseccion = new HashSet<>(A);
        interseccion.retainAll(B);
        
        Set<Integer> diferencia = new HashSet<>(A);
        diferencia.removeAll(B);
        
        System.out.println("┌─ UNIÓN (A ∪ B) ─────────────┐");
        System.out.println("│ Resultado: " + union + "      │");
        System.out.println("│ Todo lo que hay en A o B    │");
        System.out.println("└─────────────────────────────┘");
        System.out.println();
        
        System.out.println("┌─ INTERSECCIÓN (A ∩ B) ──────┐");
        System.out.println("│ Resultado: " + interseccion + "              │");
        System.out.println("│ Solo lo común a A y B       │");
        System.out.println("└─────────────────────────────┘");
        System.out.println();
        
        System.out.println("┌─ DIFERENCIA (A - B) ────────┐");
        System.out.println("│ Resultado: " + diferencia + "              │");
        System.out.println("│ Lo que está en A pero no en B│");
        System.out.println("└─────────────────────────────┘");
    }
    
    private static void visualizarOperacion(Set<Integer> A, Set<Integer> B, 
                                           Set<Integer> resultado, String tipo) {
        System.out.println("\n┌─ VISUALIZACIÓN ─────────────┐");
        System.out.println("│ Solo en A: " + calcularSoloEnA(A, B));
        System.out.println("│ En ambos:  " + calcularComunes(A, B));
        System.out.println("│ Solo en B: " + calcularSoloEnB(A, B));
        System.out.println("└─────────────────────────────┘");
    }
    
    private static Set<Integer> calcularSoloEnA(Set<Integer> A, Set<Integer> B) {
        Set<Integer> resultado = new HashSet<>(A);
        resultado.removeAll(B);
        return resultado;
    }
    
    private static Set<Integer> calcularComunes(Set<Integer> A, Set<Integer> B) {
        Set<Integer> resultado = new HashSet<>(A);
        resultado.retainAll(B);
        return resultado;
    }
    
    private static Set<Integer> calcularSoloEnB(Set<Integer> A, Set<Integer> B) {
        Set<Integer> resultado = new HashSet<>(B);
        resultado.removeAll(A);
        return resultado;
    }
    
    private static Set<Integer> seleccionarConjunto(String mensaje) {
        System.out.print(mensaje + " (nombre): ");
        String nombre = scanner.nextLine().trim().toUpperCase();
        
        if (!conjuntos.containsKey(nombre)) {
            System.out.println("✗ El conjunto '" + nombre + "' no existe.");
            return null;
        }
        
        return new HashSet<>(conjuntos.get(nombre));
    }
    
    private static <T> Set<Set<T>> generarConjuntoPotencia(Set<T> conjunto) {
        Set<Set<T>> potencia = new HashSet<>();
        
        if (conjunto.isEmpty()) {
            potencia.add(new HashSet<>());
            return potencia;
        }
        
        List<T> lista = new ArrayList<>(conjunto);
        T head = lista.get(0);
        Set<T> resto = new HashSet<>(lista.subList(1, lista.size()));
        
        for (Set<T> subconjunto : generarConjuntoPotencia(resto)) {
            potencia.add(new HashSet<>(subconjunto));
            
            Set<T> conHead = new HashSet<>(subconjunto);
            conHead.add(head);
            potencia.add(conHead);
        }
        
        return potencia;
    }
}
