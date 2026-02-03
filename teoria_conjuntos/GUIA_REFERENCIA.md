# Guía de Referencia Rápida: Conjuntos en Java 17

## 📋 Índice
1. [Interfaces y Clases](#interfaces-y-clases)
2. [Operaciones Básicas](#operaciones-básicas)
3. [Operaciones de Conjuntos](#operaciones-de-conjuntos)
4. [Comparación de Implementaciones](#comparación-de-implementaciones)
5. [Patrones Comunes](#patrones-comunes)
6. [Complejidad Temporal](#complejidad-temporal)

---

## Interfaces y Clases

### Jerarquía
```
Collection
    └── Set
        ├── HashSet
        ├── LinkedHashSet
        └── SortedSet
            └── NavigableSet
                └── TreeSet
```

### Implementaciones Principales

| Clase | Orden | Duplicados | Null | Uso Principal |
|-------|-------|------------|------|---------------|
| `HashSet` | No garantizado | No permite | Permite 1 | Búsqueda rápida |
| `LinkedHashSet` | Inserción | No permite | Permite 1 | Orden + Rapidez |
| `TreeSet` | Natural/Comparador | No permite | No permite | Datos ordenados |
| `EnumSet` | Declaración enum | No permite | No permite | Tipos enum |

---

## Operaciones Básicas

### Creación
```java
// Vacío
Set<String> set1 = new HashSet<>();

// Con elementos iniciales
Set<String> set2 = new HashSet<>(List.of("A", "B", "C"));

// Inmutable (Java 9+)
Set<String> set3 = Set.of("X", "Y", "Z");

// Copia de otro conjunto
Set<String> set4 = new HashSet<>(set2);
```

### Operaciones CRUD
```java
Set<String> conjunto = new HashSet<>();

// Agregar
conjunto.add("elemento");              // true si se agregó
conjunto.addAll(otraColeccion);        // true si cambió

// Consultar
conjunto.contains("elemento");         // true si existe
conjunto.isEmpty();                    // true si vacío
conjunto.size();                       // cantidad de elementos

// Eliminar
conjunto.remove("elemento");           // true si se eliminó
conjunto.removeAll(otraColeccion);     // elimina todos
conjunto.clear();                      // vacía el conjunto

// Retener
conjunto.retainAll(otraColeccion);     // mantiene solo intersección
```

---

## Operaciones de Conjuntos

### Notación Matemática vs Java

| Operación | Símbolo | Código Java |
|-----------|---------|-------------|
| Unión | A ∪ B | `A.addAll(B)` |
| Intersección | A ∩ B | `A.retainAll(B)` |
| Diferencia | A - B | `A.removeAll(B)` |
| Subconjunto | A ⊆ B | `B.containsAll(A)` |
| Conjunto vacío | ∅ | `new HashSet<>()` |

### Ejemplos Detallados

#### 1. Unión (A ∪ B)
```java
Set<Integer> A = Set.of(1, 2, 3);
Set<Integer> B = Set.of(3, 4, 5);

Set<Integer> union = new HashSet<>(A);
union.addAll(B);
// Resultado: {1, 2, 3, 4, 5}
```

#### 2. Intersección (A ∩ B)
```java
Set<Integer> interseccion = new HashSet<>(A);
interseccion.retainAll(B);
// Resultado: {3}
```

#### 3. Diferencia (A - B)
```java
Set<Integer> diferencia = new HashSet<>(A);
diferencia.removeAll(B);
// Resultado: {1, 2}
```

#### 4. Diferencia Simétrica (A Δ B)
```java
Set<Integer> simetrica = new HashSet<>(A);
simetrica.addAll(B);
Set<Integer> interseccion = new HashSet<>(A);
interseccion.retainAll(B);
simetrica.removeAll(interseccion);
// Resultado: {1, 2, 4, 5}
```

#### 5. Complemento (A')
```java
Set<Integer> universal = Set.of(1, 2, 3, 4, 5);
Set<Integer> A = Set.of(1, 2, 3);

Set<Integer> complemento = new HashSet<>(universal);
complemento.removeAll(A);
// Resultado: {4, 5}
```

---

## Comparación de Implementaciones

### HashSet
```java
Set<String> hashSet = new HashSet<>();
```
**Características:**
- ✅ Operaciones O(1) promedio
- ✅ Más rápido para búsquedas
- ❌ Sin orden garantizado
- 💡 Usa tabla hash internamente

**Cuándo usar:**
- Necesitas máxima velocidad
- El orden no importa
- Gran cantidad de elementos

### LinkedHashSet
```java
Set<String> linkedSet = new LinkedHashSet<>();
```
**Características:**
- ✅ Mantiene orden de inserción
- ✅ Operaciones O(1) promedio
- ⚠️ Usa más memoria que HashSet
- 💡 Lista doblemente enlazada + hash

**Cuándo usar:**
- Necesitas orden de inserción
- Quieres velocidad de HashSet
- Iterar en orden predecible

### TreeSet
```java
Set<String> treeSet = new TreeSet<>();
```
**Características:**
- ✅ Elementos siempre ordenados
- ⚠️ Operaciones O(log n)
- ❌ Más lento que HashSet
- 💡 Usa árbol rojo-negro

**Cuándo usar:**
- Necesitas elementos ordenados
- Operaciones de rango
- Navegación ordenada

### EnumSet
```java
Set<DiaSemana> enumSet = EnumSet.allOf(DiaSemana.class);
```
**Características:**
- ✅ Extremadamente eficiente para enums
- ✅ Implementación con bits
- ✅ Operaciones muy rápidas
- 💡 Solo para tipos enum

**Cuándo usar:**
- SIEMPRE con tipos enum
- Alta performance crítica

---

## Patrones Comunes

### 1. Eliminar Duplicados
```java
List<String> lista = List.of("A", "B", "A", "C");
Set<String> sinDuplicados = new LinkedHashSet<>(lista);
List<String> resultado = new ArrayList<>(sinDuplicados);
```

### 2. Verificar Elementos Comunes
```java
boolean hayComunes = !Collections.disjoint(setA, setB);
```

### 3. Convertir a Lista Ordenada
```java
List<String> listaOrdenada = new ArrayList<>(new TreeSet<>(conjunto));
```

### 4. Filtrar con Streams
```java
Set<Integer> pares = numeros.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toSet());
```

### 5. Conjuntos Inmutables
```java
// Java 9+
Set<String> inmutable = Set.of("A", "B", "C");

// Pre-Java 9
Set<String> inmutable = Collections.unmodifiableSet(
    new HashSet<>(Set.of("A", "B", "C"))
);
```

### 6. Conjunto Sincronizado (Thread-Safe)
```java
Set<String> sincronizado = Collections.synchronizedSet(new HashSet<>());
```

---

## Complejidad Temporal

### HashSet / LinkedHashSet
| Operación | Complejidad |
|-----------|-------------|
| add(e) | O(1) |
| remove(e) | O(1) |
| contains(e) | O(1) |
| size() | O(1) |
| isEmpty() | O(1) |
| clear() | O(n) |
| iterator | O(n) |

### TreeSet
| Operación | Complejidad |
|-----------|-------------|
| add(e) | O(log n) |
| remove(e) | O(log n) |
| contains(e) | O(log n) |
| first() | O(log n) |
| last() | O(log n) |
| iterator | O(n) |

### EnumSet
| Operación | Complejidad |
|-----------|-------------|
| add(e) | O(1) |
| remove(e) | O(1) |
| contains(e) | O(1) |
| Todas | O(1) |

---

## Métodos Útiles de TreeSet

```java
TreeSet<Integer> tree = new TreeSet<>(Set.of(1, 5, 3, 9, 7));

// Navegación
tree.first();           // 1 (mínimo)
tree.last();            // 9 (máximo)
tree.lower(5);          // 3 (estrictamente menor)
tree.higher(5);         // 7 (estrictamente mayor)
tree.floor(6);          // 5 (menor o igual)
tree.ceiling(6);        // 7 (mayor o igual)

// Subconjuntos
tree.headSet(5);        // {1, 3} (menores que 5)
tree.tailSet(5);        // {5, 7, 9} (mayores o iguales a 5)
tree.subSet(3, 7);      // {3, 5} ([3, 7))

// Operaciones de cola/pila
tree.pollFirst();       // Elimina y retorna mínimo
tree.pollLast();        // Elimina y retorna máximo
```

---

## Iteración

### For-each
```java
for (String elemento : conjunto) {
    System.out.println(elemento);
}
```

### Iterator
```java
Iterator<String> it = conjunto.iterator();
while (it.hasNext()) {
    String elemento = it.next();
    // it.remove(); // Eliminar durante iteración
}
```

### Streams
```java
conjunto.stream()
    .filter(e -> e.length() > 3)
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

### forEach con Lambda
```java
conjunto.forEach(elemento -> System.out.println(elemento));
conjunto.forEach(System.out::println); // Referencia a método
```

---

## Comparadores Personalizados

### TreeSet con Comparador
```java
// Por longitud de String
Set<String> porLongitud = new TreeSet<>(
    Comparator.comparing(String::length)
);

// Orden reverso
Set<Integer> reverso = new TreeSet<>(Comparator.reverseOrder());

// Múltiples criterios
Set<Persona> personas = new TreeSet<>(
    Comparator.comparing(Persona::getApellido)
              .thenComparing(Persona::getNombre)
);
```

---

## Conversiones Comunes

```java
Set<String> conjunto = new HashSet<>();

// Set → List
List<String> lista = new ArrayList<>(conjunto);

// Set → Array
String[] array = conjunto.toArray(new String[0]);

// List → Set
Set<String> deList = new HashSet<>(lista);

// Array → Set
Set<String> deArray = new HashSet<>(Arrays.asList(array));

// Stream → Set
Set<Integer> deStream = IntStream.range(1, 10)
    .boxed()
    .collect(Collectors.toSet());
```

---

## Buenas Prácticas

### ✅ HACER
- Usar `Set.of()` para conjuntos inmutables pequeños
- Especificar capacidad inicial si conoces el tamaño: `new HashSet<>(100)`
- Usar `EnumSet` SIEMPRE para enums
- Implementar `equals()` y `hashCode()` en objetos personalizados
- Usar `LinkedHashSet` cuando el orden de inserción importa

### ❌ EVITAR
- Modificar objetos después de agregarlos al Set
- Usar objetos mutables como elementos
- `HashSet` si necesitas orden
- `TreeSet` si no necesitas orden (es más lento)
- Ignorar el factor de carga en HashSet

---

## Fórmulas Matemáticas Importantes

### Cardinalidad
- |A ∪ B| = |A| + |B| - |A ∩ B|
- |P(A)| = 2^|A| (conjunto potencia)

### Leyes de De Morgan
- (A ∪ B)' = A' ∩ B'
- (A ∩ B)' = A' ∪ B'

### Propiedades
- A ∪ ∅ = A (identidad)
- A ∩ ∅ = ∅
- A ∪ A' = U (complementariedad)
- A ∩ A' = ∅

### Similitud de Jaccard
- J(A, B) = |A ∩ B| / |A ∪ B|
- Mide similitud entre conjuntos (0 = disjuntos, 1 = idénticos)

---

## Casos de Uso Reales

1. **Eliminar duplicados**: `new HashSet<>(lista)`
2. **Cache de objetos únicos**: `HashSet`
3. **Palabras clave únicas**: `HashSet`
4. **Ranking ordenado**: `TreeSet`
5. **Permisos de usuario**: `EnumSet<Permission>`
6. **Etiquetas/Tags**: `HashSet`
7. **Elementos visitados en grafo**: `HashSet`
8. **Mantener orden de inserción**: `LinkedHashSet`

---

## Errores Comunes

### 1. Modificar objetos en el Set
```java
// ❌ INCORRECTO
Set<List<Integer>> set = new HashSet<>();
List<Integer> lista = new ArrayList<>(List.of(1, 2));
set.add(lista);
lista.add(3); // ¡Modifica el objeto en el set!

// ✅ CORRECTO
set.add(new ArrayList<>(lista)); // Agrega una copia
```

### 2. No implementar equals/hashCode
```java
// ❌ INCORRECTO
class Persona {
    String nombre;
    // Sin equals/hashCode
}

// ✅ CORRECTO
class Persona {
    String nombre;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona p = (Persona) o;
        return Objects.equals(nombre, p.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
```

### 3. Asumir orden en HashSet
```java
// ❌ INCORRECTO
Set<Integer> set = new HashSet<>(List.of(1, 2, 3));
// No asumas que iterará en orden 1, 2, 3

// ✅ CORRECTO
Set<Integer> set = new LinkedHashSet<>(List.of(1, 2, 3));
// Garantiza orden de inserción
```

---

## Recursos Adicionales

- [Java Collections Framework](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/package-summary.html)
- [Effective Java (Joshua Bloch)](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Java 17 API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)

---

**Última actualización**: Enero 2025
**Versión Java**: 17 LTS
