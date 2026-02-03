# EJERCICIOS DE PROGRAMACIÓN BÁSICA EN JAVA
## Para Practicar: Ciclos, Arreglos, Condicionales y Listas

---

## 📌 EJERCICIO 1: PROMEDIO DE CALIFICACIONES

### Enunciado:
Escribe un programa que gestione las calificaciones de estudiantes. El programa debe:

**Requisitos:**
1. Recibir un arreglo de calificaciones de estudiantes (números del 0 al 100)
2. Calcular el promedio de todas las calificaciones
3. Determinar cuántos estudiantes aprobaron (calificación >= 60)
4. Determinar cuántos estudiantes reprobaron (calificación < 60)
5. Encontrar la calificación más alta y más baja
6. Mostrar qué estudiantes están por encima del promedio

**Datos de prueba:**
```java
int[] calificaciones = {85, 92, 78, 65, 45, 90, 73, 88, 55, 95};
```

**Salida esperada:**
```
Promedio general: 76.60
Aprobados: 8
Reprobados: 2
Calificación máxima: 95
Calificación mínima: 45

Estudiantes sobre el promedio:
  Estudiante 1: 85 puntos
  Estudiante 2: 92 puntos
  ...
```

**Conceptos a utilizar:**
- Arreglos
- Ciclo for
- Condicionales if-else
- Variables acumuladoras

---

## 📌 EJERCICIO 2: NÚMEROS PRIMOS

### Enunciado:
Crea un programa que trabaje con números primos:

**Requisitos:**
1. Encontrar todos los números primos menores o iguales a un número N (por ejemplo, 50)
2. Almacenarlos en una lista (ArrayList)
3. Mostrar cuántos números primos se encontraron
4. Calcular la suma de todos los números primos encontrados
5. Identificar los "primos gemelos" (primos que difieren en 2, como 11 y 13, o 17 y 19)

**Datos de prueba:**
```java
int n = 50;
```

**Salida esperada:**
```
Números primos encontrados:
[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47]

Cantidad de primos: 15
Suma de todos los primos: 328
Promedio: 21.87

Primos gemelos (diferencia de 2):
  (3, 5)
  (5, 7)
  (11, 13)
  (17, 19)
  (29, 31)
  (41, 43)
```

**Conceptos a utilizar:**
- ArrayList
- Ciclos for y while
- Método auxiliar para verificar primos
- Condicionales

**Pista para verificar si un número es primo:**
Un número es primo si solo es divisible por 1 y por sí mismo. Solo necesitas verificar divisores hasta la raíz cuadrada del número.

---

## 📌 EJERCICIO 3: INVERTIR ARREGLO

### Enunciado:
Implementa un programa que invierta un arreglo de manera eficiente:

**Requisitos:**
1. Tomar un arreglo de números enteros
2. Invertirlo SIN usar un arreglo auxiliar (in-place)
3. Mostrar el arreglo antes y después de invertirlo
4. Verificar si el arreglo original era un palíndromo (se lee igual de izquierda a derecha que de derecha a izquierda)
5. Contar cuántos intercambios se realizaron

**Datos de prueba:**
```java
// Arreglo normal
int[] numeros = {10, 20, 30, 40, 50, 60, 70};

// Arreglo palíndromo
int[] palindromo = {1, 2, 3, 2, 1};
```

**Salida esperada para arreglo normal:**
```
Arreglo original: [10, 20, 30, 40, 50, 60, 70]
Arreglo invertido: [70, 60, 50, 40, 30, 20, 10]
Intercambios realizados: 3
¿Era palíndromo?: false
```

**Salida esperada para palíndromo:**
```
Arreglo original: [1, 2, 3, 2, 1]
Arreglo invertido: [1, 2, 3, 2, 1]
Intercambios realizados: 2
¿Era palíndromo?: true
```

**Conceptos a utilizar:**
- Arreglos
- Algoritmo de dos punteros
- Variables temporales
- Ciclo while

**Algoritmo sugerido:**
1. Usa dos índices: uno al inicio y otro al final
2. Intercambia los elementos en esas posiciones
3. Mueve el índice de inicio hacia adelante y el de final hacia atrás
4. Repite hasta que los índices se crucen

---

## 📌 EJERCICIO 4: GESTIÓN DE ESTUDIANTES

### Enunciado:
Desarrolla un sistema completo de gestión de estudiantes:

**Requisitos:**
1. Almacenar nombres de estudiantes y sus calificaciones en listas paralelas (ArrayList)
2. Implementar búsqueda de un estudiante por nombre
3. Mostrar el ranking de estudiantes ordenados por calificación (de mayor a menor)
4. Identificar al estudiante con mejor y peor desempeño
5. Calcular estadísticas por rangos de calificación:
   - A: 90-100
   - B: 80-89
   - C: 70-79
   - D: 60-69
   - F: 0-59

**Datos de prueba:**
```java
Estudiantes:
Ana García - 95
Carlos López - 78
María Rodríguez - 88
Pedro Martínez - 65
Laura Sánchez - 92
Diego Torres - 45
Sofia Ramírez - 83
```

**Salida esperada:**
```
LISTA DE ESTUDIANTES:
 1. Ana García         95 (A)
 2. Carlos López       78 (C)
 3. María Rodríguez    88 (B)
 ...

Buscando: María Rodríguez
✓ Encontrado: María Rodríguez - Calificación: 88

MEJOR ESTUDIANTE:
  Ana García: 95 puntos

ESTUDIANTE CON MÁS DIFICULTADES:
  Diego Torres: 45 puntos

DISTRIBUCIÓN POR CALIFICACIÓN:
A (90-100): 2 estudiantes ██
B (80-89):  2 estudiantes ██
C (70-79):  1 estudiantes █
D (60-69):  1 estudiantes █
F (0-59):   1 estudiantes █

RANKING DE ESTUDIANTES:
1. Ana García          95 puntos 🥇
2. Laura Sánchez       92 puntos 🥈
3. María Rodríguez     88 puntos 🥉
...
```

**Conceptos a utilizar:**
- ArrayList (listas paralelas)
- Búsqueda lineal
- Ordenamiento (burbuja)
- Switch expression
- Métodos auxiliares

---

## 📌 EJERCICIO 5: ESTADÍSTICAS DE VENTAS

### Enunciado:
Crea un programa de análisis de ventas para una tienda:

**Requisitos:**
1. Almacenar las ventas de cada día de la semana en un arreglo
2. Calcular el total de ventas de la semana
3. Encontrar el día con más y menos ventas
4. Calcular el promedio diario de ventas
5. Identificar qué días estuvieron por encima del promedio
6. Simular una proyección para el próximo mes (4 semanas)
7. Detectar tendencias de ventas (crecientes, decrecientes o estables)
8. Mostrar un gráfico de barras simple con las ventas

**Datos de prueba:**
```java
String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
double[] ventas = {1250.50, 1380.75, 1420.30, 1560.90, 1890.45, 2340.80, 2150.60};
// Valores en miles de pesos
```

**Salida esperada:**
```
VENTAS DE LA SEMANA (en miles de pesos):
Lunes       : $1,250.50
Martes      : $1,380.75
Miércoles   : $1,420.30
...

ANÁLISIS GENERAL:
Total de ventas: $11,993.30
Promedio diario: $1,713.33

DÍAS DESTACADOS:
Mejor día: Sábado con $2,340.80
Peor día: Lunes con $1,250.50
Diferencia: $1,090.30 (87.2% más)

DÍAS SOBRE EL PROMEDIO:
Jueves      : $1,560.90 (+$-152.43)
Viernes     : $1,890.45 (+$177.12)
Sábado      : $2,340.80 (+$627.47)
Domingo     : $2,150.60 (+$437.27)
Total: 4 días

ANÁLISIS DE TENDENCIA:
Días con crecimiento: 5
Días con decrecimiento: 1
Tendencia general: CRECIENTE ↗

PROYECCIÓN PARA EL PRÓXIMO MES:
Tasa de crecimiento promedio: 8.32%
Proyección base (4 semanas): $47,973.20
Proyección con tendencia: $51,961.47

GRÁFICO DE VENTAS:
Lunes       : ████████████ $1,250.50
Martes      : █████████████ $1,380.75
...
```

**Conceptos a utilizar:**
- Arreglos paralelos
- Operaciones matemáticas (porcentajes, promedios)
- Condicionales complejas
- Análisis de datos secuenciales
- Formateo de números

---

## 💡 SUGERENCIAS PARA PRACTICAR

### Nivel Básico:
1. Implementa primero la funcionalidad principal de cada ejercicio
2. Prueba con los datos proporcionados
3. Verifica que obtienes los resultados esperados

### Nivel Intermedio:
1. Agrega validaciones (por ejemplo, verificar que las calificaciones estén entre 0 y 100)
2. Maneja casos especiales (arreglos vacíos, valores negativos, etc.)
3. Mejora la presentación de los resultados

### Nivel Avanzado:
1. Optimiza los algoritmos (por ejemplo, usa algoritmos de ordenamiento más eficientes)
2. Agrega más funcionalidades (por ejemplo, permitir al usuario ingresar datos)
3. Crea variaciones de los ejercicios con diferentes requisitos

---

## 📚 CONCEPTOS CLAVE A DOMINAR

### Arreglos:
- Declaración e inicialización
- Acceso a elementos por índice
- Recorrido con for y for-each
- Modificación de elementos

### Listas (ArrayList):
- Creación y manejo dinámico
- Métodos: add(), get(), set(), size(), remove()
- Ventajas sobre arreglos (tamaño dinámico)

### Ciclos:
- for: cuando conoces el número de iteraciones
- while: cuando la condición es lo importante
- for-each: para recorrer colecciones completas

### Condicionales:
- if-else: decisiones binarias
- if-else if-else: múltiples condiciones
- switch: selección entre múltiples opciones

### Buenas Prácticas:
- Nombres descriptivos de variables
- Comentarios claros
- Modularización con métodos
- Manejo de casos especiales

---

## ✅ CHECKLIST DE VERIFICACIÓN

Antes de dar por terminado cada ejercicio, verifica:

- [ ] El código compila sin errores
- [ ] Funciona con los datos de prueba proporcionados
- [ ] Los resultados coinciden con la salida esperada
- [ ] El código está bien comentado
- [ ] Las variables tienen nombres descriptivos
- [ ] Se manejan casos especiales
- [ ] El código sigue las convenciones de Java

---

## 🎯 OBJETIVOS DE APRENDIZAJE

Al completar estos ejercicios, deberías ser capaz de:

✓ Trabajar con arreglos y listas eficientemente
✓ Implementar algoritmos básicos de búsqueda y ordenamiento
✓ Usar ciclos apropiadamente para diferentes situaciones
✓ Aplicar condicionales para tomar decisiones
✓ Calcular estadísticas básicas (promedio, máximo, mínimo)
✓ Analizar y procesar datos secuenciales
✓ Modularizar código en métodos reutilizables
✓ Formatear y presentar resultados de manera clara

---

**¡Buena suerte con los ejercicios!** 🚀

Recuerda: la programación se aprende practicando. No te desanimes si algo no funciona a la primera. Cada error es una oportunidad de aprendizaje.
