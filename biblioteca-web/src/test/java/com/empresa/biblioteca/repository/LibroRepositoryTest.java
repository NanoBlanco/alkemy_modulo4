package com.empresa.biblioteca.repository;

import com.empresa.biblioteca.db.DataSourceManager;
import com.empresa.biblioteca.model.Genero;
import com.empresa.biblioteca.model.Libro;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de integración para LibroRepository.
 * Usa H2 en memoria — el DataSourceManager lee hikari.properties del classpath.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LibroRepositoryTest {

    private static LibroRepository repo;

    @BeforeAll
    static void setup() {
        DataSourceManager.inicializar();
        repo = new LibroRepository();
    }

    @AfterAll
    static void teardown() {
        DataSourceManager.cerrar();
    }

    // ── Helpers ──────────────────────────────────────────────────────────────
    private Libro libroEjemplo(String isbn) {
        return Libro.nuevo(isbn, "Clean Code", "Robert C. Martin",
                "Prentice Hall", 2008, Genero.TECNOLOGIA, 3);
    }

    // ── Tests ─────────────────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("findAll() retorna lista no nula")
    void findAll_retornaLista() {
        List<Libro> libros = repo.findAll();
        assertNotNull(libros);
        // Los datos de prueba ya fueron insertados por DataSourceManager
        assertFalse(libros.isEmpty(), "Debe haber datos de prueba");
    }

    @Test
    @Order(2)
    @DisplayName("guardar() con libro nuevo genera ID")
    void guardar_nuevoLibroGeneraId() {
        Libro nuevo = libroEjemplo("TEST-ISBN-001");
        Libro guardado = repo.guardar(nuevo);

        assertNotNull(guardado.id(), "Debe tener ID generado");
        assertEquals("TEST-ISBN-001", guardado.isbn());
        assertEquals("Clean Code", guardado.titulo());
        assertEquals(3, guardado.totalEjemplares());
        assertEquals(3, guardado.ejemplaresDisponibles());
    }

    @Test
    @Order(3)
    @DisplayName("findById() retorna el libro guardado")
    void findById_encuentraLibroGuardado() {
        Libro guardado = repo.guardar(libroEjemplo("TEST-ISBN-002"));

        Optional<Libro> encontrado = repo.findById(guardado.id());

        assertTrue(encontrado.isPresent());
        assertEquals(guardado.id(),    encontrado.get().id());
        assertEquals(guardado.titulo(), encontrado.get().titulo());
    }

    @Test
    @Order(4)
    @DisplayName("findById() retorna empty para ID inexistente")
    void findById_retornaEmptyParaIdInexistente() {
        Optional<Libro> resultado = repo.findById(99999L);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("findByIsbn() encuentra libro por ISBN")
    void findByIsbn_encuentraLibro() {
        Libro guardado = repo.guardar(libroEjemplo("TEST-ISBN-003"));

        Optional<Libro> encontrado = repo.findByIsbn("TEST-ISBN-003");

        assertTrue(encontrado.isPresent());
        assertEquals(guardado.id(), encontrado.get().id());
    }

    @Test
    @Order(6)
    @DisplayName("buscar() encuentra libros por título parcial")
    void buscar_encuentraPorTituloParcial() {
        repo.guardar(Libro.nuevo("TEST-BUSCAR-001", "Patrones de Diseño",
                "Gang of Four", "Addison", 1994, Genero.TECNOLOGIA, 2));

        List<Libro> resultados = repo.buscar("patrones");

        assertFalse(resultados.isEmpty());
        assertTrue(resultados.stream().anyMatch(l -> l.isbn().equals("TEST-BUSCAR-001")));
    }

    @Test
    @Order(7)
    @DisplayName("guardar() con ID existente actualiza el libro")
    void guardar_conIdExistenteActualiza() {
        Libro original = repo.guardar(libroEjemplo("TEST-UPDATE-001"));
        Libro modificado = new Libro(
                original.id(), original.isbn(), "Título Actualizado",
                original.autor(), original.editorial(), original.anioPublicacion(),
                original.genero(), original.totalEjemplares(),
                original.ejemplaresDisponibles(), original.creadoEn());

        Libro actualizado = repo.guardar(modificado);

        assertEquals("Título Actualizado", actualizado.titulo());
        assertEquals(original.id(), actualizado.id());
    }

    @Test
    @Order(8)
    @DisplayName("actualizarEjemplares() actualiza correctamente")
    void actualizarEjemplares_funcionaCorrectamente() {
        Libro libro = repo.guardar(libroEjemplo("TEST-EJEMPLARES-001"));

        repo.actualizarEjemplares(libro.id(), 1);

        Libro actualizado = repo.findById(libro.id()).orElseThrow();
        assertEquals(1, actualizado.ejemplaresDisponibles());
    }

    @Test
    @Order(9)
    @DisplayName("eliminar() elimina el libro y retorna true")
    void eliminar_retornaTrueYElimina() {
        Libro libro = repo.guardar(libroEjemplo("TEST-DELETE-001"));
        Long id = libro.id();

        boolean eliminado = repo.eliminar(id);

        assertTrue(eliminado);
        assertTrue(repo.findById(id).isEmpty());
    }

    @Test
    @Order(10)
    @DisplayName("eliminar() retorna false para ID inexistente")
    void eliminar_retornaFalseParaInexistente() {
        boolean resultado = repo.eliminar(99999L);
        assertFalse(resultado);
    }

    @Test
    @Order(11)
    @DisplayName("contarTotal() retorna número positivo")
    void contarTotal_retornaPositivo() {
        long total = repo.contarTotal();
        assertTrue(total > 0);
    }

    @Test
    @Order(12)
    @DisplayName("Record Libro es inmutable — prestar no modifica el original")
    void libro_esInmutable() {
        Libro libro = libroEjemplo("TEST-INMUTABLE-001");

        Libro prestado = libro.prestar();

        // El original no cambia (Records son inmutables)
        assertEquals(3, libro.ejemplaresDisponibles());
        assertEquals(2, prestado.ejemplaresDisponibles());
    }

    @Test
    @Order(13)
    @DisplayName("Libro con ISBN null lanza NullPointerException")
    void libro_isbnNull_lanzaExcepcion() {
        assertThrows(NullPointerException.class, () ->
                Libro.nuevo(null, "Titulo", "Autor", "Ed", 2020, Genero.FICCION, 1));
    }

    @Test
    @Order(14)
    @DisplayName("Libro con año inválido lanza IllegalArgumentException")
    void libro_anioInvalido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                Libro.nuevo("ISBN-X", "Titulo", "Autor", "Ed", 1000, Genero.FICCION, 1));
    }
}
