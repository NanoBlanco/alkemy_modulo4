import java.util.*;
import java.util.stream.Collectors;

/**
 * EJERCICIOS PRÁCTICOS DE TEORÍA DE CONJUNTOS
 * 
 * Este archivo contiene ejercicios resueltos que demuestran
 * aplicaciones prácticas de la teoría de conjuntos en Java
 */
public class EjerciciosConjuntos {
    
    public static void main(String[] args) {
        System.out.println("=== EJERCICIOS PRÁCTICOS ===\n");
        
        ejercicio1_GestionEstudiantes();
        ejercicio2_AnalisisVentas();
        ejercicio3_RedesSociales();
        ejercicio4_SistemaPermisos();
        ejercicio5_AnalizadorTexto();
        ejercicio6_GrafoRelaciones();
    }
    
    /**
     * EJERCICIO 1: Gestión de Estudiantes por Curso
     * 
     * Problema: Tienes listas de estudiantes inscritos en diferentes cursos.
     * Necesitas:
     * a) Encontrar estudiantes en múltiples cursos
     * b) Estudiantes solo en un curso específico
     * c) Total de estudiantes únicos
     */
    private static void ejercicio1_GestionEstudiantes() {
        System.out.println("--- EJERCICIO 1: Gestión de Estudiantes ---\n");
        
        Set<String> java = new HashSet<>(
            Set.of("Ana", "Carlos", "María", "Pedro", "Luis")
        );
        Set<String> python = new HashSet<>(
            Set.of("María", "Pedro", "Carmen", "José")
        );
        Set<String> javascript = new HashSet<>(
            Set.of("Ana", "Carmen", "Luis", "Roberto")
        );
        
        System.out.println("Estudiantes en Java: " + java);
        System.out.println("Estudiantes en Python: " + python);
        System.out.println("Estudiantes en JavaScript: " + javascript);
        System.out.println();
        
        // a) Estudiantes en múltiples cursos
        Set<String> enJavaYPython = new HashSet<>(java);
        enJavaYPython.retainAll(python);
        System.out.println("Estudiantes en Java Y Python: " + enJavaYPython);
        
        Set<String> enTresCursos = new HashSet<>(java);
        enTresCursos.retainAll(python);
        enTresCursos.retainAll(javascript);
        System.out.println("Estudiantes en los 3 cursos: " + enTresCursos);
        
        // b) Solo en Java (no en otros)
        Set<String> soloJava = new HashSet<>(java);
        soloJava.removeAll(python);
        soloJava.removeAll(javascript);
        System.out.println("Solo en Java: " + soloJava);
        
        // c) Total de estudiantes únicos
        Set<String> todosEstudiantes = new HashSet<>(java);
        todosEstudiantes.addAll(python);
        todosEstudiantes.addAll(javascript);
        System.out.println("Total de estudiantes únicos: " + todosEstudiantes.size());
        System.out.println("Lista completa: " + todosEstudiantes);
        
        System.out.println();
    }
    
    /**
     * EJERCICIO 2: Análisis de Ventas por Región
     * 
     * Problema: Analizar qué productos se venden en diferentes regiones
     */
    private static void ejercicio2_AnalisisVentas() {
        System.out.println("--- EJERCICIO 2: Análisis de Ventas ---\n");
        
        Set<String> norte = new HashSet<>(
            Set.of("Laptop", "Mouse", "Teclado", "Monitor", "Webcam")
        );
        Set<String> sur = new HashSet<>(
            Set.of("Mouse", "Teclado", "Auriculares", "Micrófono")
        );
        Set<String> este = new HashSet<>(
            Set.of("Laptop", "Monitor", "Auriculares", "SSD")
        );
        
        System.out.println("Productos vendidos por región:");
        System.out.println("Norte: " + norte);
        System.out.println("Sur: " + sur);
        System.out.println("Este: " + este);
        System.out.println();
        
        // Productos vendidos en todas las regiones
        Set<String> enTodasRegiones = new HashSet<>(norte);
        enTodasRegiones.retainAll(sur);
        enTodasRegiones.retainAll(este);
        System.out.println("Productos en todas las regiones: " + enTodasRegiones);
        
        // Productos vendidos en al menos una región
        Set<String> todosProductos = new HashSet<>(norte);
        todosProductos.addAll(sur);
        todosProductos.addAll(este);
        System.out.println("Catálogo total: " + todosProductos);
        
        // Productos exclusivos de cada región
        Set<String> exclusivosNorte = new HashSet<>(norte);
        exclusivosNorte.removeAll(sur);
        exclusivosNorte.removeAll(este);
        System.out.println("Exclusivos del Norte: " + exclusivosNorte);
        
        // Productos en exactamente dos regiones
        Set<String> enDosRegiones = new HashSet<>();
        for (String producto : todosProductos) {
            int contador = 0;
            if (norte.contains(producto)) contador++;
            if (sur.contains(producto)) contador++;
            if (este.contains(producto)) contador++;
            if (contador == 2) {
                enDosRegiones.add(producto);
            }
        }
        System.out.println("Productos en exactamente 2 regiones: " + enDosRegiones);
        
        System.out.println();
    }
    
    /**
     * EJERCICIO 3: Redes Sociales - Amigos en Común
     * 
     * Problema: Sistema de recomendación de amigos
     */
    private static void ejercicio3_RedesSociales() {
        System.out.println("--- EJERCICIO 3: Redes Sociales ---\n");
        
        // Mapa de usuarios y sus amigos
        Map<String, Set<String>> redesSocial = new HashMap<>();
        redesSocial.put("Ana", Set.of("Carlos", "María", "Pedro", "Luis"));
        redesSocial.put("Carlos", Set.of("Ana", "María", "José"));
        redesSocial.put("María", Set.of("Ana", "Carlos", "Pedro", "Carmen"));
        redesSocial.put("Pedro", Set.of("Ana", "María", "Luis"));
        redesSocial.put("Luis", Set.of("Ana", "Pedro", "Roberto"));
        
        String usuario1 = "Ana";
        String usuario2 = "Luis";
        
        System.out.println("Amigos de " + usuario1 + ": " + redesSocial.get(usuario1));
        System.out.println("Amigos de " + usuario2 + ": " + redesSocial.get(usuario2));
        
        // Amigos en común
        Set<String> amigosComunes = new HashSet<>(redesSocial.get(usuario1));
        amigosComunes.retainAll(redesSocial.get(usuario2));
        System.out.println("Amigos en común: " + amigosComunes);
        
        // Sugerencias de amistad para usuario1
        // (amigos de sus amigos que él no conoce)
        Set<String> sugerencias = new HashSet<>();
        for (String amigo : redesSocial.get(usuario1)) {
            if (redesSocial.containsKey(amigo)) {
                sugerencias.addAll(redesSocial.get(amigo));
            }
        }
        sugerencias.removeAll(redesSocial.get(usuario1));
        sugerencias.remove(usuario1);
        
        System.out.println("Sugerencias de amistad para " + usuario1 + ": " + sugerencias);
        
        // Calcular grado de separación
        System.out.println("\nGrado de conexión entre Ana y Roberto:");
        List<String> camino = encontrarCamino(redesSocial, "Ana", "Roberto");
        System.out.println("Camino: " + String.join(" → ", camino));
        System.out.println("Grados de separación: " + (camino.size() - 1));
        
        System.out.println();
    }
    
    /**
     * EJERCICIO 4: Sistema de Permisos y Roles
     * 
     * Problema: Gestión de permisos basada en roles
     */
    private static void ejercicio4_SistemaPermisos() {
        System.out.println("--- EJERCICIO 4: Sistema de Permisos ---\n");
        
        // Definir permisos por rol
        Map<String, Set<String>> permisosPorRol = new HashMap<>();
        permisosPorRol.put("Admin", Set.of("LEER", "ESCRIBIR", "ELIMINAR", "EJECUTAR", "CONFIGURAR"));
        permisosPorRol.put("Editor", Set.of("LEER", "ESCRIBIR", "EJECUTAR"));
        permisosPorRol.put("Visualizador", Set.of("LEER"));
        permisosPorRol.put("Moderador", Set.of("LEER", "ESCRIBIR", "ELIMINAR"));
        
        // Usuario con múltiples roles
        Set<String> rolesUsuario = Set.of("Editor", "Moderador");
        
        System.out.println("Roles del usuario: " + rolesUsuario);
        
        // Calcular permisos efectivos (unión de todos los roles)
        Set<String> permisosEfectivos = new HashSet<>();
        for (String rol : rolesUsuario) {
            permisosEfectivos.addAll(permisosPorRol.get(rol));
        }
        System.out.println("Permisos efectivos: " + permisosEfectivos);
        
        // Verificar si puede realizar acción
        String accion = "ELIMINAR";
        boolean puedeRealizarAccion = permisosEfectivos.contains(accion);
        System.out.println("¿Puede " + accion + "? " + puedeRealizarAccion);
        
        // Permisos que le faltan para ser Admin
        Set<String> permisosAdmin = permisosPorRol.get("Admin");
        Set<String> permisosFaltantes = new HashSet<>(permisosAdmin);
        permisosFaltantes.removeAll(permisosEfectivos);
        System.out.println("Permisos que le faltan para ser Admin: " + permisosFaltantes);
        
        // Roles mínimos necesarios para ciertos permisos
        Set<String> permisosRequeridos = Set.of("LEER", "ESCRIBIR", "EJECUTAR");
        System.out.println("\nRoles que tienen todos estos permisos: " + permisosRequeridos);
        for (Map.Entry<String, Set<String>> entrada : permisosPorRol.entrySet()) {
            if (entrada.getValue().containsAll(permisosRequeridos)) {
                System.out.println("  - " + entrada.getKey());
            }
        }
        
        System.out.println();
    }
    
    /**
     * EJERCICIO 5: Analizador de Texto
     * 
     * Problema: Análisis de palabras únicas, frecuencia, etc.
     */
    private static void ejercicio5_AnalizadorTexto() {
        System.out.println("--- EJERCICIO 5: Analizador de Texto ---\n");
        
        String texto1 = "Java es un lenguaje de programación orientado a objetos";
        String texto2 = "Python es un lenguaje de programación interpretado y versátil";
        
        Set<String> palabras1 = obtenerPalabras(texto1);
        Set<String> palabras2 = obtenerPalabras(texto2);
        
        System.out.println("Texto 1: " + texto1);
        System.out.println("Palabras únicas: " + palabras1);
        System.out.println("Total: " + palabras1.size() + " palabras únicas\n");
        
        System.out.println("Texto 2: " + texto2);
        System.out.println("Palabras únicas: " + palabras2);
        System.out.println("Total: " + palabras2.size() + " palabras únicas\n");
        
        // Palabras comunes
        Set<String> comunes = new HashSet<>(palabras1);
        comunes.retainAll(palabras2);
        System.out.println("Palabras en ambos textos: " + comunes);
        
        // Palabras exclusivas
        Set<String> soloTexto1 = new HashSet<>(palabras1);
        soloTexto1.removeAll(palabras2);
        System.out.println("Solo en texto 1: " + soloTexto1);
        
        // Vocabulario total
        Set<String> vocabularioTotal = new HashSet<>(palabras1);
        vocabularioTotal.addAll(palabras2);
        System.out.println("Vocabulario total: " + vocabularioTotal.size() + " palabras");
        
        // Similitud de Jaccard: |A ∩ B| / |A ∪ B|
        double similitud = (double) comunes.size() / vocabularioTotal.size();
        System.out.printf("Similitud de Jaccard: %.2f%%\n", similitud * 100);
        
        System.out.println();
    }
    
    /**
     * EJERCICIO 6: Grafo de Relaciones
     * 
     * Problema: Modelar relaciones y encontrar conexiones
     */
    private static void ejercicio6_GrafoRelaciones() {
        System.out.println("--- EJERCICIO 6: Grafo de Relaciones ---\n");
        
        // Grafo de dependencias entre tareas
        Map<String, Set<String>> dependencias = new HashMap<>();
        dependencias.put("Diseño", new HashSet<>());
        dependencias.put("Frontend", Set.of("Diseño"));
        dependencias.put("Backend", Set.of("Diseño"));
        dependencias.put("Base de Datos", Set.of("Diseño"));
        dependencias.put("Integración", Set.of("Frontend", "Backend", "Base de Datos"));
        dependencias.put("Testing", Set.of("Integración"));
        dependencias.put("Deployment", Set.of("Testing"));
        
        System.out.println("Dependencias del proyecto:");
        for (Map.Entry<String, Set<String>> entrada : dependencias.entrySet()) {
            System.out.println(entrada.getKey() + " depende de: " + 
                             (entrada.getValue().isEmpty() ? "nada" : entrada.getValue()));
        }
        
        // Tareas que no tienen dependencias (pueden empezar inmediatamente)
        Set<String> tareasSinDependencias = dependencias.entrySet().stream()
            .filter(e -> e.getValue().isEmpty())
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
        System.out.println("\nTareas que pueden iniciar: " + tareasSinDependencias);
        
        // Tareas que dependen directamente de "Diseño"
        Set<String> dependenDeDiseño = dependencias.entrySet().stream()
            .filter(e -> e.getValue().contains("Diseño"))
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
        System.out.println("Tareas que dependen de Diseño: " + dependenDeDiseño);
        
        // Orden topológico (orden válido de ejecución)
        System.out.println("\nOrden sugerido de ejecución:");
        List<String> ordenTopologico = ordenTopologico(dependencias);
        for (int i = 0; i < ordenTopologico.size(); i++) {
            System.out.println((i + 1) + ". " + ordenTopologico.get(i));
        }
        
        System.out.println();
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    /**
     * Extrae palabras únicas de un texto (en minúsculas)
     */
    private static Set<String> obtenerPalabras(String texto) {
        return Arrays.stream(texto.toLowerCase().split("\\s+"))
                     .collect(Collectors.toSet());
    }
    
    /**
     * Encuentra un camino entre dos nodos en un grafo (BFS)
     */
    private static List<String> encontrarCamino(
            Map<String, Set<String>> grafo, String inicio, String fin) {
        
        Queue<List<String>> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        
        cola.offer(List.of(inicio));
        visitados.add(inicio);
        
        while (!cola.isEmpty()) {
            List<String> camino = cola.poll();
            String actual = camino.get(camino.size() - 1);
            
            if (actual.equals(fin)) {
                return camino;
            }
            
            if (grafo.containsKey(actual)) {
                for (String vecino : grafo.get(actual)) {
                    if (!visitados.contains(vecino)) {
                        visitados.add(vecino);
                        List<String> nuevoCamino = new ArrayList<>(camino);
                        nuevoCamino.add(vecino);
                        cola.offer(nuevoCamino);
                    }
                }
            }
        }
        
        return new ArrayList<>(); // No hay camino
    }
    
    /**
     * Orden topológico usando algoritmo de Kahn
     */
    private static List<String> ordenTopologico(Map<String, Set<String>> dependencias) {
        List<String> resultado = new ArrayList<>();
        Map<String, Integer> gradoEntrada = new HashMap<>();
        
        // Calcular grado de entrada
        for (String nodo : dependencias.keySet()) {
            gradoEntrada.putIfAbsent(nodo, 0);
        }
        for (Set<String> deps : dependencias.values()) {
            for (String dep : deps) {
                gradoEntrada.put(dep, gradoEntrada.getOrDefault(dep, 0) + 1);
            }
        }
        
        // Cola con nodos sin dependencias
        Queue<String> cola = new LinkedList<>();
        for (Map.Entry<String, Integer> entrada : gradoEntrada.entrySet()) {
            if (entrada.getValue() == 0) {
                cola.offer(entrada.getKey());
            }
        }
        
        // Procesar nodos
        while (!cola.isEmpty()) {
            String nodo = cola.poll();
            resultado.add(nodo);
            
            // Reducir grado de entrada de dependientes
            for (Map.Entry<String, Set<String>> entrada : dependencias.entrySet()) {
                if (entrada.getValue().contains(nodo)) {
                    String dependiente = entrada.getKey();
                    gradoEntrada.put(dependiente, gradoEntrada.get(dependiente) - 1);
                    if (gradoEntrada.get(dependiente) == 0) {
                        cola.offer(dependiente);
                    }
                }
            }
        }
        
        return resultado;
    }
}
