# GUÍA TEÓRICA: CONCEPTOS BÁSICOS DE PROGRAMACIÓN EN JAVA

## 📖 Contenido

1. [Arreglos (Arrays)](#arreglos-arrays)
2. [Listas (ArrayList)](#listas-arraylist)
3. [Ciclos](#ciclos)
4. [Condicionales](#condicionales)
5. [Patrones Comunes](#patrones-comunes)
6. [Complejidad Algoritmica](#complejidad-algoritmica)

---

## 1. ARREGLOS (ARRAYS)

### ¿Qué es un arreglo?
Un arreglo es una estructura de datos que almacena una colección de elementos del mismo tipo en posiciones consecutivas de memoria.

### Características:
- **Tamaño fijo**: Una vez creado, no puede cambiar de tamaño
- **Tipo homogéneo**: Todos los elementos deben ser del mismo tipo
- **Indexación**: Se accede a elementos mediante índices (0 a n-1)
- **Eficiencia**: Acceso rápido O(1) a cualquier elemento

### Declaración e Inicialización:

```java
// Forma 1: Declarar y crear
int[] numeros = new int[5];  // Arreglo de 5 enteros, inicializado en 0

// Forma 2: Declarar e inicializar con valores
int[] numeros = {10, 20, 30, 40, 50};

// Forma 3: Declaración separada
int[] numeros;
numeros = new int[5];

// Otros tipos
String[] nombres = {"Ana", "Carlos", "María"};
double[] precios = {19.99, 29.99, 39.99};
boolean[] estados = {true, false, true};
```

### Operaciones Básicas:

```java
int[] arr = {10, 20, 30, 40, 50};

// Acceder a un elemento
int primero = arr[0];        // 10
int ultimo = arr[arr.length - 1];  // 50

// Modificar un elemento
arr[2] = 100;  // arr ahora es {10, 20, 100, 40, 50}

// Obtener longitud
int tamaño = arr.length;  // 5

// Recorrer con for
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// Recorrer con for-each
for (int numero : arr) {
    System.out.println(numero);
}
```

### Arreglos Multidimensionales:

```java
// Matriz 2D (filas x columnas)
int[][] matriz = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Acceder a elementos
int elemento = matriz[1][2];  // 6 (fila 1, columna 2)

// Recorrer matriz
for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j] + " ");
    }
    System.out.println();
}
```

### Métodos Útiles de la Clase Arrays:

```java
import java.util.Arrays;

int[] arr = {5, 2, 8, 1, 9};

// Ordenar
Arrays.sort(arr);  // {1, 2, 5, 8, 9}

// Buscar (requiere arreglo ordenado)
int index = Arrays.binarySearch(arr, 5);  // Retorna índice

// Copiar
int[] copia = Arrays.copyOf(arr, arr.length);

// Llenar con un valor
Arrays.fill(arr, 0);  // Todos los elementos = 0

// Comparar
boolean iguales = Arrays.equals(arr1, arr2);

// Convertir a String
String texto = Arrays.toString(arr);  // "[1, 2, 5, 8, 9]"
```

---

## 2. LISTAS (ARRAYLIST)

### ¿Qué es un ArrayList?
Es una implementación de lista dinámica que puede crecer o reducirse según sea necesario.

### Ventajas sobre Arreglos:
- ✅ Tamaño dinámico (crece automáticamente)
- ✅ Métodos convenientes (add, remove, contains, etc.)
- ✅ Parte del Collections Framework

### Desventajas:
- ⚠️ Solo almacena objetos (no primitivos directamente)
- ⚠️ Ligeramente más lento que arreglos para acceso

### Uso Básico:

```java
import java.util.ArrayList;
import java.util.List;

// Crear ArrayList
ArrayList<String> nombres = new ArrayList<>();
// O mejor, usar la interfaz:
List<String> nombres = new ArrayList<>();

// Agregar elementos
nombres.add("Ana");
nombres.add("Carlos");
nombres.add("María");

// Agregar en posición específica
nombres.add(1, "Luis");  // Inserta en índice 1

// Obtener elemento
String primero = nombres.get(0);

// Modificar elemento
nombres.set(0, "Andrea");

// Eliminar elemento
nombres.remove(0);           // Por índice
nombres.remove("Carlos");    // Por valor

// Verificar si existe
boolean existe = nombres.contains("María");

// Obtener tamaño
int tamaño = nombres.size();

// Verificar si está vacía
boolean vacia = nombres.isEmpty();

// Limpiar toda la lista
nombres.clear();
```

### Tipos Genéricos:

```java
// Con diferentes tipos
List<Integer> numeros = new ArrayList<>();
List<Double> precios = new ArrayList<>();
List<Boolean> flags = new ArrayList<>();

// NOTA: No se pueden usar tipos primitivos directamente
// List<int> numeros = new ArrayList<>();  // ❌ ERROR

// Usar clases wrapper
List<Integer> numeros = new ArrayList<>();  // ✅ CORRECTO
```

### Conversión entre Arreglos y Listas:

```java
// Arreglo a Lista
String[] arrayNombres = {"Ana", "Carlos", "María"};
List<String> listaNombres = new ArrayList<>(Arrays.asList(arrayNombres));

// Lista a Arreglo
List<String> lista = new ArrayList<>();
lista.add("Ana");
lista.add("Carlos");
String[] array = lista.toArray(new String[0]);
```

### Recorrer un ArrayList:

```java
List<Integer> numeros = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));

// Forma 1: for tradicional
for (int i = 0; i < numeros.size(); i++) {
    System.out.println(numeros.get(i));
}

// Forma 2: for-each
for (Integer numero : numeros) {
    System.out.println(numero);
}

// Forma 3: Iterator
Iterator<Integer> iterator = numeros.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

// Forma 4: forEach con lambda (Java 8+)
numeros.forEach(numero -> System.out.println(numero));

// Forma 5: forEach con referencia a método
numeros.forEach(System.out::println);
```

---

## 3. CICLOS

### For Loop

**Cuándo usar:** Cuando conoces el número exacto de iteraciones.

```java
// Sintaxis básica
for (inicialización; condición; actualización) {
    // código
}

// Ejemplo: imprimir números del 1 al 10
for (int i = 1; i <= 10; i++) {
    System.out.println(i);
}

// Recorrer arreglo
int[] arr = {10, 20, 30, 40, 50};
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// Recorrer en reversa
for (int i = arr.length - 1; i >= 0; i--) {
    System.out.println(arr[i]);
}

// Incremento personalizado
for (int i = 0; i < 100; i += 10) {
    System.out.println(i);  // 0, 10, 20, ..., 90
}

// Múltiples variables
for (int i = 0, j = 10; i < j; i++, j--) {
    System.out.println(i + " " + j);
}
```

### For-Each Loop (Enhanced For)

**Cuándo usar:** Para recorrer colecciones cuando no necesitas el índice.

```java
// Con arreglos
int[] numeros = {1, 2, 3, 4, 5};
for (int numero : numeros) {
    System.out.println(numero);
}

// Con listas
List<String> nombres = Arrays.asList("Ana", "Carlos", "María");
for (String nombre : nombres) {
    System.out.println(nombre);
}

// LIMITACIÓN: No puedes modificar el arreglo
for (int numero : numeros) {
    numero = numero * 2;  // ❌ No modifica el arreglo original
}

// Para modificar, usa for tradicional
for (int i = 0; i < numeros.length; i++) {
    numeros[i] = numeros[i] * 2;  // ✅ Modifica el arreglo
}
```

### While Loop

**Cuándo usar:** Cuando no sabes cuántas iteraciones necesitarás.

```java
// Sintaxis
while (condición) {
    // código
}

// Ejemplo: leer entrada hasta que sea válida
Scanner scanner = new Scanner(System.in);
int numero = -1;
while (numero < 0 || numero > 100) {
    System.out.print("Ingresa un número entre 0 y 100: ");
    numero = scanner.nextInt();
}

// Búsqueda en arreglo
int[] arr = {10, 20, 30, 40, 50};
int buscar = 30;
int i = 0;
while (i < arr.length && arr[i] != buscar) {
    i++;
}
if (i < arr.length) {
    System.out.println("Encontrado en índice: " + i);
}

// Procesar hasta condición
int suma = 0;
int contador = 1;
while (suma < 100) {
    suma += contador;
    contador++;
}
```

### Do-While Loop

**Cuándo usar:** Cuando necesitas ejecutar el código al menos una vez.

```java
// Sintaxis
do {
    // código
} while (condición);

// Ejemplo: menú que se muestra al menos una vez
int opcion;
do {
    System.out.println("1. Opción 1");
    System.out.println("2. Opción 2");
    System.out.println("0. Salir");
    opcion = scanner.nextInt();
} while (opcion != 0);

// Validación de entrada
int edad;
do {
    System.out.print("Ingresa tu edad: ");
    edad = scanner.nextInt();
    if (edad < 0) {
        System.out.println("Edad inválida");
    }
} while (edad < 0);
```

### Control de Flujo en Ciclos:

```java
// break: Sale del ciclo inmediatamente
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;  // Sale cuando i = 5
    }
    System.out.println(i);  // Imprime 0, 1, 2, 3, 4
}

// continue: Salta a la siguiente iteración
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) {
        continue;  // Salta números pares
    }
    System.out.println(i);  // Imprime solo impares: 1, 3, 5, 7, 9
}

// return: Sale del método completo
public int buscar(int[] arr, int valor) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == valor) {
            return i;  // Retorna el índice
        }
    }
    return -1;  // No encontrado
}
```

---

## 4. CONDICIONALES

### If-Else

**Uso básico:**

```java
int edad = 18;

// If simple
if (edad >= 18) {
    System.out.println("Es mayor de edad");
}

// If-else
if (edad >= 18) {
    System.out.println("Es mayor de edad");
} else {
    System.out.println("Es menor de edad");
}

// If-else if-else
if (edad < 13) {
    System.out.println("Niño");
} else if (edad < 18) {
    System.out.println("Adolescente");
} else if (edad < 60) {
    System.out.println("Adulto");
} else {
    System.out.println("Adulto mayor");
}
```

### Operadores de Comparación:

```java
int a = 10, b = 20;

// Igualdad
if (a == b) { }  // false

// Diferencia
if (a != b) { }  // true

// Mayor que
if (a > b) { }   // false

// Mayor o igual
if (a >= b) { }  // false

// Menor que
if (a < b) { }   // true

// Menor o igual
if (a <= b) { }  // true
```

### Operadores Lógicos:

```java
boolean llueve = true;
boolean tengosParaguas = false;
int temperatura = 25;

// AND (&&): Ambas condiciones deben ser verdaderas
if (temperatura > 30 && llueve) {
    System.out.println("Día caluroso y lluvioso");
}

// OR (||): Al menos una condición debe ser verdadera
if (llueve || temperatura < 10) {
    System.out.println("Mal clima");
}

// NOT (!): Invierte el valor booleano
if (!tengosParaguas && llueve) {
    System.out.println("Te vas a mojar");
}

// Combinación
if ((temperatura > 30 || temperatura < 0) && !llueve) {
    System.out.println("Temperatura extrema pero sin lluvia");
}
```

### Operador Ternario:

```java
// Sintaxis: condición ? valorSiTrue : valorSiFalse

int edad = 18;
String tipo = edad >= 18 ? "Mayor" : "Menor";

// Equivalente a:
String tipo;
if (edad >= 18) {
    tipo = "Mayor";
} else {
    tipo = "Menor";
}

// Ejemplo práctico
int a = 10, b = 20;
int max = (a > b) ? a : b;

// Anidado (no recomendado, dificulta lectura)
String categoria = edad < 13 ? "Niño" : (edad < 18 ? "Adolescente" : "Adulto");
```

### Switch Statement:

**Cuándo usar:** Cuando comparas una variable contra múltiples valores constantes.

```java
// Switch tradicional
int dia = 3;
String nombreDia;

switch (dia) {
    case 1:
        nombreDia = "Lunes";
        break;
    case 2:
        nombreDia = "Martes";
        break;
    case 3:
        nombreDia = "Miércoles";
        break;
    case 4:
        nombreDia = "Jueves";
        break;
    case 5:
        nombreDia = "Viernes";
        break;
    case 6:
    case 7:
        nombreDia = "Fin de semana";
        break;
    default:
        nombreDia = "Día inválido";
}

// Switch expression (Java 14+)
String nombreDia = switch (dia) {
    case 1 -> "Lunes";
    case 2 -> "Martes";
    case 3 -> "Miércoles";
    case 4 -> "Jueves";
    case 5 -> "Viernes";
    case 6, 7 -> "Fin de semana";
    default -> "Día inválido";
};

// Con bloques de código
String mensaje = switch (dia) {
    case 1, 2, 3, 4, 5 -> {
        System.out.println("Es día laboral");
        yield "A trabajar!";
    }
    case 6, 7 -> {
        System.out.println("Es fin de semana");
        yield "A descansar!";
    }
    default -> "Día inválido";
};
```

---

## 5. PATRONES COMUNES

### 1. Calcular Suma y Promedio

```java
int[] numeros = {10, 20, 30, 40, 50};

// Suma
int suma = 0;
for (int numero : numeros) {
    suma += numero;
}
System.out.println("Suma: " + suma);

// Promedio
double promedio = (double) suma / numeros.length;
System.out.println("Promedio: " + promedio);
```

### 2. Encontrar Máximo y Mínimo

```java
int[] numeros = {34, 12, 56, 23, 89, 15};

int maximo = numeros[0];
int minimo = numeros[0];

for (int i = 1; i < numeros.length; i++) {
    if (numeros[i] > maximo) {
        maximo = numeros[i];
    }
    if (numeros[i] < minimo) {
        minimo = numeros[i];
    }
}

System.out.println("Máximo: " + maximo);
System.out.println("Mínimo: " + minimo);
```

### 3. Contar Elementos que Cumplen una Condición

```java
int[] numeros = {10, 15, 20, 25, 30, 35, 40};

// Contar pares
int pares = 0;
for (int numero : numeros) {
    if (numero % 2 == 0) {
        pares++;
    }
}
System.out.println("Números pares: " + pares);

// Contar mayores a 20
int mayoresA20 = 0;
for (int numero : numeros) {
    if (numero > 20) {
        mayoresA20++;
    }
}
```

### 4. Buscar un Elemento (Búsqueda Lineal)

```java
int[] numeros = {10, 20, 30, 40, 50};
int buscar = 30;

int indice = -1;
for (int i = 0; i < numeros.length; i++) {
    if (numeros[i] == buscar) {
        indice = i;
        break;
    }
}

if (indice != -1) {
    System.out.println("Encontrado en índice: " + indice);
} else {
    System.out.println("No encontrado");
}
```

### 5. Invertir un Arreglo (In-Place)

```java
int[] arr = {10, 20, 30, 40, 50};

int inicio = 0;
int fin = arr.length - 1;

while (inicio < fin) {
    // Intercambiar
    int temp = arr[inicio];
    arr[inicio] = arr[fin];
    arr[fin] = temp;
    
    inicio++;
    fin--;
}
// Resultado: {50, 40, 30, 20, 10}
```

### 6. Ordenamiento Burbuja (Bubble Sort)

```java
int[] arr = {64, 34, 25, 12, 22, 11, 90};

for (int i = 0; i < arr.length - 1; i++) {
    for (int j = 0; j < arr.length - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
            // Intercambiar
            int temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
        }
    }
}
// Resultado: {11, 12, 22, 25, 34, 64, 90}
```

### 7. Filtrar Elementos

```java
List<Integer> numeros = Arrays.asList(10, 15, 20, 25, 30, 35, 40);
List<Integer> pares = new ArrayList<>();

for (Integer numero : numeros) {
    if (numero % 2 == 0) {
        pares.add(numero);
    }
}
// pares contiene: [10, 20, 30, 40]
```

### 8. Acumulador con Condición

```java
int[] precios = {100, 250, 150, 300, 200};
int descuento = 0;

// Sumar solo precios mayores a 200
int totalDescuento = 0;
for (int precio : precios) {
    if (precio > 200) {
        totalDescuento += precio * 0.1; // 10% descuento
    }
}
```

---

## 6. COMPLEJIDAD ALGORÍTMICA

### Notación Big O

Mide la eficiencia de un algoritmo en términos de tiempo o espacio.

| Notación | Nombre | Ejemplo |
|----------|--------|---------|
| O(1) | Constante | Acceder a un elemento de arreglo |
| O(log n) | Logarítmica | Búsqueda binaria |
| O(n) | Lineal | Búsqueda lineal, recorrer arreglo |
| O(n log n) | Linealítmica | Merge sort, Quick sort |
| O(n²) | Cuadrática | Ordenamiento burbuja, dos ciclos anidados |
| O(2ⁿ) | Exponencial | Algunos algoritmos recursivos |

### Ejemplos:

```java
// O(1) - Constante
int primero = arr[0];  // Siempre toma el mismo tiempo

// O(n) - Lineal
int suma = 0;
for (int numero : arr) {  // Recorre n elementos
    suma += numero;
}

// O(n²) - Cuadrática
for (int i = 0; i < n; i++) {        // n veces
    for (int j = 0; j < n; j++) {    // n veces cada una
        // código
    }
}

// O(log n) - Logarítmica (búsqueda binaria)
int busquedaBinaria(int[] arr, int objetivo) {
    int inicio = 0, fin = arr.length - 1;
    while (inicio <= fin) {
        int medio = (inicio + fin) / 2;
        if (arr[medio] == objetivo) return medio;
        if (arr[medio] < objetivo) inicio = medio + 1;
        else fin = medio - 1;
    }
    return -1;
}
```

---

## 📝 CONSEJOS Y MEJORES PRÁCTICAS

### 1. Naming (Nomenclatura)
```java
// ❌ MALO
int a = 10;
int x[] = {1, 2, 3};

// ✅ BUENO
int edad = 10;
int[] calificaciones = {85, 90, 78};
```

### 2. Magic Numbers
```java
// ❌ MALO
if (edad >= 18) { }

// ✅ BUENO
final int EDAD_MAYORIA = 18;
if (edad >= EDAD_MAYORIA) { }
```

### 3. Validaciones
```java
// Siempre valida entradas
if (arr == null || arr.length == 0) {
    System.out.println("Arreglo vacío");
    return;
}

// Valida índices
if (indice >= 0 && indice < arr.length) {
    // Acceso seguro
}
```

### 4. Comentarios Útiles
```java
// ❌ MALO
i++;  // incrementa i

// ✅ BUENO
// Calcular el promedio de calificaciones aprobatorias (>= 60)
double promedio = sumaAprobados / contadorAprobados;
```

### 5. DRY (Don't Repeat Yourself)
```java
// ❌ MALO - código repetido
int suma1 = arr1[0] + arr1[1] + arr1[2];
int suma2 = arr2[0] + arr2[1] + arr2[2];

// ✅ BUENO - método reutilizable
public int sumar(int[] arr) {
    int suma = 0;
    for (int num : arr) {
        suma += num;
    }
    return suma;
}
```

---

## 🎯 RESUMEN DE CUÁNDO USAR QUÉ

| Necesito... | Usa... |
|-------------|--------|
| Almacenar elementos de tamaño fijo | `Array` |
| Almacenar elementos de tamaño dinámico | `ArrayList` |
| Sé cuántas iteraciones necesito | `for` |
| No sé cuántas iteraciones necesito | `while` |
| Necesito ejecutar al menos una vez | `do-while` |
| Solo recorrer una colección | `for-each` |
| Comparar contra múltiples valores | `switch` |
| Decisión binaria | `if-else` |

---

**Esta guía cubre los fundamentos necesarios para resolver los 5 ejercicios propuestos. ¡Úsala como referencia mientras practicas!** 📚
