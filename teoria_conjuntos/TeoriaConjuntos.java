import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase completa de Teoría de Conjuntos en Java 17
 * Incluye todas las operaciones básicas y avanzadas
 */
public class TeoriaConjuntos {
    
    public static void main(String[] args) {
        System.out.println("=== TEORÍA DE CONJUNTOS EN JAVA 17 ===\n");
        
        // 1. Creación de Conjuntos
        demostrarCreacionConjuntos();
        
        // 2. Operaciones Básicas
        demostrarOperacionesBasicas();
        
        // 3. Operaciones de Conjuntos
        demostrarOperacionesConjuntos();
        
        // 4. Métodos de Verificación
        demostrarMetodosVerificacion();
        
        // 5. Iteración y Streams
        demostrarIteracionYStreams();
        
        // 6. Conjuntos Especiales
        demostrarConjuntosEspeciales();
        
        // 7. Ejemplos Prácticos del Mundo Real
        ejemplosPracticos();
    }
    
    /**
     * 1. CREACIÓN DE CONJUNTOS
     */
    private static void demostrarCreacionConjuntos() {
        System.out.println("--- 1. CREACIÓN DE CONJUNTOS ---\n");
        
        // HashSet - No mantiene orden
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(2);
        System.out.println("HashSet: " + hashSet);
        
        // LinkedHashSet - Mantiene orden de inserción
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(3);
        linkedHashSet.add(1);
        linkedHashSet.add(2);
        System.out.println("LinkedHashSet: " + linkedHashSet);
        
        // TreeSet - Mantiene orden natural (ordenado)
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);
        System.out.println("TreeSet: " + treeSet);
        
        // Crear conjunto desde colección
        Set<String> conjunto1 = new HashSet<>(List.of("A", "B", "C"));
        System.out.println("Conjunto desde List: " + conjunto1);
        
        // Crear conjunto inmutable (Java 9+)
        Set<String> conjuntoInmutable = Set.of("X", "Y", "Z");
        System.out.println("Conjunto inmutable: " + conjuntoInmutable);
        
        System.out.println();
    }
    
    /**
     * 2. OPERACIONES BÁSICAS
     */
    private static void demostrarOperacionesBasicas() {
        System.out.println("--- 2. OPERACIONES BÁSICAS ---\n");
        
        Set<String> frutas = new HashSet<>();
        
        // Agregar elementos
        frutas.add("Manzana");
        frutas.add("Banana");
        frutas.add("Naranja");
        frutas.add("Manzana"); // Duplicado - no se agrega
        System.out.println("Frutas después de agregar: " + frutas);
        System.out.println("Tamaño: " + frutas.size());
        
        // Verificar si contiene
        System.out.println("¿Contiene 'Banana'? " + frutas.contains("Banana"));
        System.out.println("¿Contiene 'Pera'? " + frutas.contains("Pera"));
        
        // Eliminar elemento
        frutas.remove("Banana");
        System.out.println("Después de eliminar 'Banana': " + frutas);
        
        // Verificar si está vacío
        System.out.println("¿Está vacío? " + frutas.isEmpty());
        
        // Limpiar conjunto
        Set<String> temporal = new HashSet<>(frutas);
        temporal.clear();
        System.out.println("Después de clear(): " + temporal);
        
        System.out.println();
    }
    
    /**
     * 3. OPERACIONES DE CONJUNTOS (Teoría Matemática)
     */
    private static void demostrarOperacionesConjuntos() {
        System.out.println("--- 3. OPERACIONES DE CONJUNTOS ---\n");
        
        Set<Integer> A = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Set.of(4, 5, 6, 7, 8));
        
        System.out.println("Conjunto A: " + A);
        System.out.println("Conjunto B: " + B);
        System.out.println();
        
        // UNIÓN (A ∪ B)
        Set<Integer> union = new HashSet<>(A);
        union.addAll(B);
        System.out.println("Unión (A ∪ B): " + union);
        
        // INTERSECCIÓN (A ∩ B)
        Set<Integer> interseccion = new HashSet<>(A);
        interseccion.retainAll(B);
        System.out.println("Intersección (A ∩ B): " + interseccion);
        
        // DIFERENCIA (A - B)
        Set<Integer> diferencia = new HashSet<>(A);
        diferencia.removeAll(B);
        System.out.println("Diferencia (A - B): " + diferencia);
        
        // DIFERENCIA SIMÉTRICA (A Δ B)
        Set<Integer> diferenciaSimetrica = new HashSet<>(A);
        diferenciaSimetrica.addAll(B);
        Set<Integer> interseccionTemp = new HashSet<>(A);
        interseccionTemp.retainAll(B);
        diferenciaSimetrica.removeAll(interseccionTemp);
        System.out.println("Diferencia Simétrica (A Δ B): " + diferenciaSimetrica);
        
        // COMPLEMENTO (requiere conjunto universal)
        Set<Integer> universal = new HashSet<>(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Set<Integer> complemento = new HashSet<>(universal);
        complemento.removeAll(A);
        System.out.println("Complemento de A (respecto a U={1..10}): " + complemento);
        
        System.out.println();
    }
    
    /**
     * 4. MÉTODOS DE VERIFICACIÓN
     */
    private static void demostrarMetodosVerificacion() {
        System.out.println("--- 4. MÉTODOS DE VERIFICACIÓN ---\n");
        
        Set<Integer> A = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> B = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> C = new HashSet<>(Set.of(6, 7, 8));
        
        System.out.println("Conjunto A: " + A);
        System.out.println("Conjunto B: " + B);
        System.out.println("Conjunto C: " + C);
        System.out.println();
        
        // Es subconjunto (A ⊆ B)
        boolean esSubconjunto = B.containsAll(A);
        System.out.println("¿A es subconjunto de B? " + esSubconjunto);
        
        // Es superconjunto (B ⊇ A)
        boolean esSuperconjunto = A.containsAll(B);
        System.out.println("¿A es superconjunto de B? " + esSuperconjunto);
        
        // Son disjuntos (A ∩ C = ∅)
        boolean sonDisjuntos = Collections.disjoint(A, C);
        System.out.println("¿A y C son disjuntos? " + sonDisjuntos);
        
        // Son iguales
        Set<Integer> D = new HashSet<>(Set.of(3, 2, 1));
        boolean sonIguales = A.equals(D);
        System.out.println("¿A y D son iguales? " + sonIguales);
        
        System.out.println();
    }
    
    /**
     * 5. ITERACIÓN Y STREAMS (Java 17)
     */
    private static void demostrarIteracionYStreams() {
        System.out.println("--- 5. ITERACIÓN Y STREAMS ---\n");
        
        Set<Integer> numeros = new HashSet<>(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        
        // Iteración tradicional
        System.out.print("For-each: ");
        for (Integer num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Iteración con Iterator
        System.out.print("Iterator: ");
        Iterator<Integer> iterator = numeros.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // Filtrar con Streams
        Set<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toSet());
        System.out.println("Números pares: " + pares);
        
        // Transformar elementos
        Set<String> numerosTexto = numeros.stream()
                .map(n -> "Número-" + n)
                .collect(Collectors.toSet());
        System.out.println("Transformados: " + numerosTexto);
        
        // Operaciones con Streams
        int suma = numeros.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Suma de elementos: " + suma);
        
        OptionalInt maximo = numeros.stream().mapToInt(Integer::intValue).max();
        maximo.ifPresent(max -> System.out.println("Máximo: " + max));
        
        System.out.println();
    }
    
    /**
     * 6. CONJUNTOS ESPECIALES
     */
    private static void demostrarConjuntosEspeciales() {
        System.out.println("--- 6. CONJUNTOS ESPECIALES ---\n");
        
        // Conjunto vacío
        Set<String> vacio = new HashSet<>();
        System.out.println("Conjunto vacío: " + vacio + ", tamaño: " + vacio.size());
        
        // Conjunto singleton (un solo elemento)
        Set<String> singleton = Collections.singleton("Único");
        System.out.println("Conjunto singleton: " + singleton);
        
        // TreeSet con comparador personalizado
        Set<String> palabras = new TreeSet<>(Comparator.comparing(String::length));
        palabras.add("Java");
        palabras.add("Python");
        palabras.add("C");
        palabras.add("JavaScript");
        System.out.println("TreeSet ordenado por longitud: " + palabras);
        
        // EnumSet - altamente eficiente para enums
        Set<DiaSemana> diasLaborales = EnumSet.range(DiaSemana.LUNES, DiaSemana.VIERNES);
        System.out.println("Días laborales: " + diasLaborales);
        
        // Conjunto sincronizado (thread-safe)
        Set<Integer> sincronizado = Collections.synchronizedSet(new HashSet<>());
        sincronizado.add(1);
        sincronizado.add(2);
        System.out.println("Conjunto sincronizado: " + sincronizado);
        
        // Conjunto no modificable
        Set<String> noModificable = Collections.unmodifiableSet(
            new HashSet<>(Set.of("A", "B", "C"))
        );
        System.out.println("Conjunto no modificable: " + noModificable);
        
        System.out.println();
    }
    
    /**
     * 7. EJEMPLOS PRÁCTICOS DEL MUNDO REAL
     */
    private static void ejemplosPracticos() {
        System.out.println("--- 7. EJEMPLOS PRÁCTICOS ---\n");
        
        // Ejemplo 1: Eliminar duplicados de una lista
        System.out.println("Ejemplo 1: Eliminar duplicados");
        List<String> listaDuplicados = List.of("A", "B", "A", "C", "B", "D");
        Set<String> sinDuplicados = new LinkedHashSet<>(listaDuplicados);
        System.out.println("Lista original: " + listaDuplicados);
        System.out.println("Sin duplicados: " + sinDuplicados);
        System.out.println();
        
        // Ejemplo 2: Encontrar elementos comunes entre dos listas
        System.out.println("Ejemplo 2: Elementos comunes");
        Set<String> estudiantes1 = new HashSet<>(Set.of("Ana", "Juan", "María", "Pedro"));
        Set<String> estudiantes2 = new HashSet<>(Set.of("María", "Luis", "Pedro", "Carmen"));
        Set<String> comunes = new HashSet<>(estudiantes1);
        comunes.retainAll(estudiantes2);
        System.out.println("Estudiantes en ambos grupos: " + comunes);
        System.out.println();
        
        // Ejemplo 3: Sistema de etiquetas (tags)
        System.out.println("Ejemplo 3: Sistema de etiquetas");
        Set<String> etiquetasArticulo1 = new HashSet<>(Set.of("java", "programación", "tutorial"));
        Set<String> etiquetasArticulo2 = new HashSet<>(Set.of("java", "desarrollo", "backend"));
        
        Set<String> todasEtiquetas = new HashSet<>(etiquetasArticulo1);
        todasEtiquetas.addAll(etiquetasArticulo2);
        System.out.println("Todas las etiquetas: " + todasEtiquetas);
        
        Set<String> etiquetasCompartidas = new HashSet<>(etiquetasArticulo1);
        etiquetasCompartidas.retainAll(etiquetasArticulo2);
        System.out.println("Etiquetas compartidas: " + etiquetasCompartidas);
        System.out.println();
        
        // Ejemplo 4: Verificar permisos
        System.out.println("Ejemplo 4: Sistema de permisos");
        Set<String> permisosRequeridos = Set.of("LEER", "ESCRIBIR");
        Set<String> permisosUsuario = new HashSet<>(Set.of("LEER", "ESCRIBIR", "EJECUTAR"));
        
        boolean tienePermisos = permisosUsuario.containsAll(permisosRequeridos);
        System.out.println("¿Usuario tiene todos los permisos? " + tienePermisos);
        System.out.println();
        
        // Ejemplo 5: Conjunto potencia (Power Set)
        System.out.println("Ejemplo 5: Conjunto potencia");
        Set<Integer> conjunto = Set.of(1, 2, 3);
        Set<Set<Integer>> potencia = generarConjuntoPotencia(conjunto);
        System.out.println("Conjunto: " + conjunto);
        System.out.println("Conjunto potencia: " + potencia);
        System.out.println("Cardinalidad: 2^" + conjunto.size() + " = " + potencia.size());
    }
    
    /**
     * Genera el conjunto potencia de un conjunto dado
     * P(A) = conjunto de todos los subconjuntos de A
     */
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
            Set<T> nuevoSubconjunto = new HashSet<>(subconjunto);
            potencia.add(nuevoSubconjunto);
            
            nuevoSubconjunto = new HashSet<>(subconjunto);
            nuevoSubconjunto.add(head);
            potencia.add(nuevoSubconjunto);
        }
        
        return potencia;
    }
    
    /**
     * Enum para ejemplo de EnumSet
     */
    enum DiaSemana {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }
}
