# 🗂️ Blog JPA — Ejercicio Integrador
### Java 17 · Spring Boot 3.5.1 · Spring Data JPA · MySQL

---

## 📐 Arquitectura de Capas

```
┌──────────────────────────────────────────────────┐
│              CLIENTE (Postman / Frontend)         │
└────────────────────┬─────────────────────────────┘
                     │  HTTP Request/Response (JSON)
┌────────────────────▼─────────────────────────────┐
│         CAPA CONTROLLER  (@RestController)        │
│  UsuarioController · PostController               │
│  EtiquetaController                               │
│  → Recibe HTTP, valida, delega, retorna respuesta │
└────────────────────┬─────────────────────────────┘
                     │  Llama a
┌────────────────────▼─────────────────────────────┐
│          CAPA SERVICE  (@Service)                 │
│  UsuarioServiceImpl · PostServiceImpl             │
│  EtiquetaServiceImpl                              │
│  → Lógica de negocio, @Transactional              │
└────────────────────┬─────────────────────────────┘
                     │  Llama a
┌────────────────────▼─────────────────────────────┐
│        CAPA REPOSITORY  (@Repository)             │
│  UsuarioRepository · PostRepository               │
│  EtiquetaRepository · PostEtiquetaRepository      │
│  → JpaRepository + JPQL con JOIN FETCH            │
└────────────────────┬─────────────────────────────┘
                     │  JPA / Hibernate
┌────────────────────▼─────────────────────────────┐
│          CAPA ENTITY  (@Entity)                   │
│  Usuario · Perfil · Post · Etiqueta               │
│  PostEtiqueta · PostEtiquetaId                    │
│  → Mapeo objeto-relacional (ORM)                  │
└────────────────────┬─────────────────────────────┘
                     │  JDBC
┌────────────────────▼─────────────────────────────┐
│             MySQL — Base de datos: blog           │
└──────────────────────────────────────────────────┘
```

---

## 🗃️ Diagrama de Relaciones JPA

```
┌──────────┐     1:1      ┌──────────┐
│ Usuario  │◄────────────►│  Perfil  │
│          │              │ (FK: usuario_id)
└────┬─────┘              └──────────┘
     │ 1:N
     │ (FK: autor_id en posts)
┌────▼─────┐     N:M      ┌──────────────┐
│   Post   │◄────────────►│   Etiqueta   │
│          │              │              │
└──────────┘              └──────────────┘
     │
     │ (tabla intermedia con atributos extra)
┌────▼───────────────┐
│   PostEtiqueta     │
│  PK: (post_id,     │
│       etiqueta_id) │
│  + fecha_aplicacion│
│  + notas           │
└────────────────────┘
```

---

## ⚙️ Configuración

### 1. Crear la base de datos MySQL

```sql
CREATE DATABASE blog CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'bloguser'@'localhost' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON blog.* TO 'bloguser'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Configurar credenciales

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog?...
spring.datasource.username=bloguser
spring.datasource.password=tu_password
```

### 3. Ejecutar la aplicación

```bash
# Modo normal
mvn spring-boot:run

# Modo desarrollo (con datos de prueba precargados)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Ejecutar tests (usa H2 en memoria, no requiere MySQL)
mvn test
```

---

## 🌐 Endpoints REST

### Usuarios

| Método | URL | Descripción |
|--------|-----|-------------|
| POST   | `/api/v1/usuarios` | Crear usuario (con perfil) |
| GET    | `/api/v1/usuarios` | Listar usuarios activos |
| GET    | `/api/v1/usuarios/{id}` | Obtener usuario por ID |
| PUT    | `/api/v1/usuarios/{id}` | Actualizar datos del usuario |
| PATCH  | `/api/v1/usuarios/{id}/perfil` | Actualizar perfil |
| DELETE | `/api/v1/usuarios/{id}` | Desactivar usuario |

### Posts

| Método | URL | Descripción |
|--------|-----|-------------|
| POST   | `/api/v1/posts` | Crear post |
| GET    | `/api/v1/posts` | Listar publicados (paginado) |
| GET    | `/api/v1/posts/{id}` | Obtener post por ID |
| GET    | `/api/v1/posts/autor/{autorId}` | Posts de un autor |
| GET    | `/api/v1/posts/buscar?titulo=X` | Buscar por título |
| GET    | `/api/v1/posts/etiqueta/{nombre}` | Posts por etiqueta |
| PUT    | `/api/v1/posts/{id}` | Actualizar post |
| PATCH  | `/api/v1/posts/{id}/publicar` | Publicar post |
| PATCH  | `/api/v1/posts/{id}/estado?estado=ARCHIVADO` | Cambiar estado |
| DELETE | `/api/v1/posts/{id}` | Eliminar post |
| POST   | `/api/v1/posts/{postId}/etiquetas/{etiquetaId}` | Agregar etiqueta |
| DELETE | `/api/v1/posts/{postId}/etiquetas/{etiquetaId}` | Remover etiqueta |
| GET    | `/api/v1/posts/{postId}/etiquetas/detalle` | Etiquetas con fecha |

### Etiquetas

| Método | URL | Descripción |
|--------|-----|-------------|
| POST   | `/api/v1/etiquetas` | Crear etiqueta |
| GET    | `/api/v1/etiquetas` | Listar todas |
| GET    | `/api/v1/etiquetas/mas-usadas` | Top etiquetas |
| GET    | `/api/v1/etiquetas/{id}` | Obtener por ID |
| PUT    | `/api/v1/etiquetas/{id}` | Actualizar |
| DELETE | `/api/v1/etiquetas/{id}` | Eliminar (si no está en uso) |

---

## 📋 Ejemplos de Requests (JSON)

### Crear usuario con perfil
```json
POST /api/v1/usuarios
{
  "nombre": "Ana García",
  "email": "ana@blog.com",
  "password": "123456",
  "perfil": {
    "bio": "Desarrolladora Java con 5 años de experiencia",
    "sitioWeb": "https://anagarcia.dev",
    "ubicacion": "Santiago"
  }
}
```

### Crear post con etiquetas
```json
POST /api/v1/posts
{
  "titulo": "Introducción a JPA",
  "contenido": "JPA es el estándar de Java para persistencia...",
  "resumen": "Aprende JPA desde cero",
  "autorId": 1,
  "etiquetaIds": [1, 2, 3]
}
```

### Crear etiqueta
```json
POST /api/v1/etiquetas
{
  "nombre": "java",
  "color": "#F89820"
}
```

---

## 📁 Estructura del Proyecto

```
src/main/java/com/curso/blog/
├── BlogJpaApplication.java          # Clase principal
├── config/
│   └── DataInitializer.java         # Datos de prueba (perfil dev)
├── entity/
│   ├── Usuario.java                 # @OneToOne, @OneToMany
│   ├── Perfil.java                  # @OneToOne propietario
│   ├── Post.java                    # @ManyToOne, @ManyToMany
│   ├── Etiqueta.java                # @ManyToMany inverso
│   ├── PostEtiqueta.java            # Entidad intermedia con atributos
│   └── PostEtiquetaId.java          # Clave compuesta @EmbeddedId
├── repository/
│   ├── UsuarioRepository.java       # JpaRepository + JPQL
│   ├── PostRepository.java
│   ├── EtiquetaRepository.java
│   └── PostEtiquetaRepository.java
├── service/
│   ├── UsuarioService.java          # Interface
│   ├── PostService.java
│   ├── EtiquetaService.java
│   └── impl/
│       ├── UsuarioServiceImpl.java  # Implementación + @Transactional
│       ├── PostServiceImpl.java
│       └── EtiquetaServiceImpl.java
├── controller/
│   ├── UsuarioController.java       # REST endpoints
│   ├── PostController.java
│   └── EtiquetaController.java
├── dto/
│   ├── request/RequestDTOs.java     # DTOs de entrada (@Valid)
│   └── response/ResponseDTOs.java  # DTOs de salida
├── mapper/
│   └── BlogMapper.java              # Conversión Entity ↔ DTO
└── exception/
    ├── BlogExceptions.java          # Excepciones del dominio
    └── GlobalExceptionHandler.java  # @RestControllerAdvice
```
