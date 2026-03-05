package com.empresa.biblioteca.service;

import com.empresa.biblioteca.model.Genero;
import com.empresa.biblioteca.model.Libro;
import com.empresa.biblioteca.model.Resultado;
import com.empresa.biblioteca.repository.LibroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE — Lógica de negocio para operaciones sobre Libros.
 *
 * Responsabilidades:
 *   - Reglas de negocio (validaciones, restricciones)
 *   - Coordinación entre repositorios si fuera necesario
 *   - Retorna Resultado<T> para indicar éxito/error sin excepciones de control
 *
 * NO conoce HTTP, ni Servlets, ni cómo se accede a la BD.
 */
public class LibroService {

    private static final Logger log = LoggerFactory.getLogger(LibroService.class);

    private final LibroRepository libroRepo;

    public LibroService(LibroRepository libroRepo) {
        this.libroRepo = libroRepo;
    }

    public List<Libro> listarTodos() {
        return libroRepo.findAll();
    }

    public List<Libro> buscar(String termino) {
        if (termino == null || termino.isBlank()) return listarTodos();
        return libroRepo.buscar(termino.trim());
    }

    public Optional<Libro> buscarPorId(Long id) {
        return libroRepo.findById(id);
    }

    public Resultado<Libro> registrar(String isbn, String titulo, String autor,
                                      String editorial, int anio,
                                      String generoStr, int ejemplares) {
        // Validación: ISBN único
        if (libroRepo.findByIsbn(isbn).isPresent())
            return Resultado.error("Ya existe un libro con ISBN: " + isbn);

        // Validación: género válido
        Genero genero;
        try {
            genero = Genero.valueOf(generoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Resultado.error("Género inválido: " + generoStr);
        }

        try {
            Libro nuevo = Libro.nuevo(isbn.trim(), titulo.trim(), autor.trim(),
                                      editorial, anio, genero, ejemplares);
            Libro guardado = libroRepo.guardar(nuevo);
            log.info("Libro registrado: {} - {}", guardado.isbn(), guardado.titulo());
            return Resultado.ok(guardado);
        } catch (IllegalArgumentException e) {
            return Resultado.error(e.getMessage());
        }
    }

    public Resultado<Libro> actualizar(Long id, String isbn, String titulo, String autor,
                                       String editorial, int anio,
                                       String generoStr, int totalEjemplares) {
        Optional<Libro> existente = libroRepo.findById(id);
        if (existente.isEmpty())
            return Resultado.error("Libro no encontrado con id: " + id);

        // Validar que el nuevo ISBN no pertenezca a OTRO libro
        Optional<Libro> porIsbn = libroRepo.findByIsbn(isbn);
        if (porIsbn.isPresent() && !porIsbn.get().id().equals(id))
            return Resultado.error("El ISBN " + isbn + " ya está en uso por otro libro");

        Genero genero;
        try {
            genero = Genero.valueOf(generoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Resultado.error("Género inválido: " + generoStr);
        }

        Libro actual = existente.get();
        // Recalcular disponibles: mantener la diferencia (prestados = total - disponibles)
        int prestados = actual.totalEjemplares() - actual.ejemplaresDisponibles();
        int nuevosDisponibles = Math.max(0, totalEjemplares - prestados);

        try {
            Libro actualizado = new Libro(id, isbn.trim(), titulo.trim(), autor.trim(),
                    editorial, anio, genero, totalEjemplares, nuevosDisponibles, actual.creadoEn());
            Libro guardado = libroRepo.guardar(actualizado);
            log.info("Libro actualizado id={}", id);
            return Resultado.ok(guardado);
        } catch (IllegalArgumentException e) {
            return Resultado.error(e.getMessage());
        }
    }

    public Resultado<Void> eliminar(Long id) {
        if (libroRepo.findById(id).isEmpty())
            return Resultado.error("Libro no encontrado con id: " + id);

        boolean eliminado = libroRepo.eliminar(id);
        return eliminado ? Resultado.ok(null)
                        : Resultado.error("No se pudo eliminar el libro id: " + id);
    }

    public long contarTotal()        { return libroRepo.contarTotal(); }
    public long contarDisponibles()  { return libroRepo.contarDisponibles(); }
}
