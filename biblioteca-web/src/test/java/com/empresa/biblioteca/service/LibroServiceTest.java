package com.empresa.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.empresa.biblioteca.model.Genero;
import com.empresa.biblioteca.model.Libro;
import com.empresa.biblioteca.model.Resultado;
import com.empresa.biblioteca.repository.LibroRepository;

/**
 * Tests unitarios de LibroService con Mockito.
 * El repositorio está mockeado: no toca la base de datos.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de LibroService")
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepo;

    private LibroService service;

    // Libro de ejemplo reutilizable
    private final Libro libroEjemplo = new Libro(
            1L, "ISBN-001", "Clean Code", "Robert C. Martin",
            "Prentice Hall", 2008, Genero.TECNOLOGIA, 3, 3,
            LocalDateTime.now());

    @BeforeEach
    void setUp() {
        service = new LibroService(libroRepo);
    }

    // ── registrar ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("registrar() con datos válidos retorna Exito")
    void registrar_datosValidos_retornaExito() {
        when(libroRepo.findByIsbn("ISBN-001")).thenReturn(Optional.empty());
        when(libroRepo.guardar(any())).thenReturn(libroEjemplo);

        Resultado<Libro> resultado = service.registrar(
                "ISBN-001", "Clean Code", "Robert C. Martin",
                "Prentice Hall", 2008, "TECNOLOGIA", 3);

        assertTrue(resultado.esExito());
        assertEquals("Clean Code", resultado.getValor().titulo());
        verify(libroRepo, times(1)).guardar(any());
    }

    @Test
    @DisplayName("registrar() con ISBN duplicado retorna Error")
    void registrar_isbnDuplicado_retornaError() {
        when(libroRepo.findByIsbn("ISBN-001")).thenReturn(Optional.of(libroEjemplo));

        Resultado<Libro> resultado = service.registrar(
                "ISBN-001", "Otro Libro", "Otro Autor",
                "Ed", 2020, "FICCION", 1);

        assertTrue(resultado.esError());
        assertTrue(resultado.getMensajeError().contains("ISBN"));
        verify(libroRepo, never()).guardar(any()); // no debe intentar guardar
    }

    @Test
    @DisplayName("registrar() con género inválido retorna Error")
    void registrar_generoInvalido_retornaError() {
        when(libroRepo.findByIsbn(anyString())).thenReturn(Optional.empty());

        Resultado<Libro> resultado = service.registrar(
                "ISBN-999", "Titulo", "Autor", "Ed", 2020, "GENERO_INEXISTENTE", 1);

        assertTrue(resultado.esError());
        assertTrue(resultado.getMensajeError().contains("Género"));
    }

    // ── actualizar ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("actualizar() libro existente retorna Exito")
    void actualizar_libroExistente_retornaExito() {
        Libro actualizado = new Libro(1L, "ISBN-001", "Clean Code 2a Ed",
                "Robert C. Martin", "Prentice Hall", 2022, Genero.TECNOLOGIA, 3, 3, LocalDateTime.now());

        when(libroRepo.findById(1L)).thenReturn(Optional.of(libroEjemplo));
        when(libroRepo.findByIsbn("ISBN-001")).thenReturn(Optional.of(libroEjemplo));
        when(libroRepo.guardar(any())).thenReturn(actualizado);

        Resultado<Libro> resultado = service.actualizar(
                1L, "ISBN-001", "Clean Code 2a Ed",
                "Robert C. Martin", "Prentice Hall", 2022, "TECNOLOGIA", 3);

        assertTrue(resultado.esExito());
        assertEquals("Clean Code 2a Ed", resultado.getValor().titulo());
    }

    @Test
    @DisplayName("actualizar() libro inexistente retorna Error")
    void actualizar_libroInexistente_retornaError() {
        when(libroRepo.findById(999L)).thenReturn(Optional.empty());

        Resultado<Libro> resultado = service.actualizar(
                999L, "ISBN", "T", "A", "E", 2020, "FICCION", 1);

        assertTrue(resultado.esError());
        assertTrue(resultado.getMensajeError().contains("no encontrado"));
    }

    // ── eliminar ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("eliminar() libro existente retorna Exito")
    void eliminar_libroExistente_retornaExito() {
        when(libroRepo.findById(1L)).thenReturn(Optional.of(libroEjemplo));
        when(libroRepo.eliminar(1L)).thenReturn(true);

        Resultado<Void> resultado = service.eliminar(1L);

        assertTrue(resultado.esExito());
    }

    @Test
    @DisplayName("eliminar() libro inexistente retorna Error")
    void eliminar_libroInexistente_retornaError() {
        when(libroRepo.findById(999L)).thenReturn(Optional.empty());

        Resultado<Void> resultado = service.eliminar(999L);

        assertTrue(resultado.esError());
        verify(libroRepo, never()).eliminar(anyLong());
    }

    // ── listar ────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("listarTodos() delega al repositorio")
    void listarTodos_delegaAlRepositorio() {
        when(libroRepo.findAll()).thenReturn(List.of(libroEjemplo));

        List<Libro> libros = service.listarTodos();

        assertEquals(1, libros.size());
        verify(libroRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("buscar() con término vacío retorna todos los libros")
    void buscar_terminoVacio_retornaTodos() {
        when(libroRepo.findAll()).thenReturn(List.of(libroEjemplo));

        List<Libro> libros = service.buscar("");

        assertEquals(1, libros.size());
        verify(libroRepo, times(1)).findAll();
        verify(libroRepo, never()).buscar(any());
    }

    // ── Resultado sealed class ────────────────────────────────────────────────

    @Test
    @DisplayName("Resultado.ok() tiene valor, Resultado.error() tiene mensaje")
    void resultado_sealedClass_funcionaCorrectamente() {
        Resultado<String> exito = Resultado.ok("valor");
        Resultado<String> error = Resultado.error("algo salió mal");

        assertTrue(exito.esExito());
        assertFalse(exito.esError());
        assertEquals("valor", exito.getValor());

        assertTrue(error.esError());
        assertFalse(error.esExito());
        assertEquals("algo salió mal", error.getMensajeError());
    }

    @Test
    @DisplayName("getValor() en Error lanza excepción")
    void resultado_getValorEnError_lanzaExcepcion() {
        Resultado<String> error = Resultado.error("error");
        assertThrows(IllegalStateException.class, error::getValor);
    }

    @Test
    @DisplayName("getMensajeError() en Exito lanza excepción")
    void resultado_getMensajeEnExito_lanzaExcepcion() {
        Resultado<String> exito = Resultado.ok("ok");
        assertThrows(IllegalStateException.class, exito::getMensajeError);
    }
}
