package com.empresa.biblioteca.service;

import com.empresa.biblioteca.model.Libro;
import com.empresa.biblioteca.model.Prestamo;
import com.empresa.biblioteca.model.Resultado;
import com.empresa.biblioteca.repository.LibroRepository;
import com.empresa.biblioteca.repository.PrestamoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE — Lógica de negocio para Préstamos.
 */
public class PrestamoService {

    private static final Logger log = LoggerFactory.getLogger(PrestamoService.class);

    private final PrestamoRepository prestamoRepo;
    private final LibroRepository    libroRepo;

    public PrestamoService(PrestamoRepository prestamoRepo, LibroRepository libroRepo) {
        this.prestamoRepo = prestamoRepo;
        this.libroRepo    = libroRepo;
    }

    public List<Prestamo> listarTodos()   { return prestamoRepo.findAll(); }
    public List<Prestamo> listarActivos() { return prestamoRepo.findActivos(); }

    public Optional<Prestamo> buscarPorId(Long id) {
        return prestamoRepo.findById(id);
    }

    public Resultado<Prestamo> prestar(Long libroId, String nombre, String email) {
        // Validar que el libro existe
        Optional<Libro> opt = libroRepo.findById(libroId);
        if (opt.isEmpty())
            return Resultado.error("Libro no encontrado con id: " + libroId);

        Libro libro = opt.get();

        // Regla de negocio: debe tener ejemplares disponibles
        if (!libro.disponible())
            return Resultado.error("No hay ejemplares disponibles de: " + libro.titulo());

        // Validar datos del usuario
        if (nombre == null || nombre.isBlank())
            return Resultado.error("El nombre del usuario es obligatorio");
        if (email == null || !email.contains("@"))
            return Resultado.error("Email inválido: " + email);

        Prestamo nuevoPrestamo = Prestamo.nuevo(
                libroId, libro.titulo(), libro.isbn(),
                nombre.trim(), email.trim().toLowerCase());

        // El repositorio maneja la transacción: INSERT prestamo + UPDATE ejemplares
        Prestamo guardado = prestamoRepo.guardar(
                nuevoPrestamo, libroRepo, libro.ejemplaresDisponibles() - 1);

        log.info("Préstamo exitoso: libro='{}' usuario='{}'", libro.titulo(), nombre);
        return Resultado.ok(guardado);
    }

    public Resultado<Prestamo> devolver(Long prestamoId) {
        Optional<Prestamo> opt = prestamoRepo.findById(prestamoId);
        if (opt.isEmpty())
            return Resultado.error("Préstamo no encontrado con id: " + prestamoId);

        Prestamo prestamo = opt.get();
        if (!prestamo.estaActivo())
            return Resultado.error("El préstamo id=" + prestamoId + " ya fue devuelto");

        // El repositorio maneja la transacción: UPDATE prestamo + UPDATE ejemplares
        Prestamo devuelto = prestamoRepo.devolver(prestamoId, libroRepo);
        log.info("Devolución exitosa préstamo id={}", prestamoId);
        return Resultado.ok(devuelto);
    }

    public long contarActivos() { return prestamoRepo.contarActivos(); }
}
