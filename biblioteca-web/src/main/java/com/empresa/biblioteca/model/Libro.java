package com.empresa.biblioteca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * MODEL — Record inmutable que representa un Libro.
 * Java 17 Records: constructor, getters, equals, hashCode y toString automáticos.
 */
public record Libro(
        Long          id,
        String        isbn,
        String        titulo,
        String        autor,
        String        editorial,
        int           anioPublicacion,
        Genero        genero,
        int           totalEjemplares,
        int           ejemplaresDisponibles,
        LocalDateTime creadoEn
) {
    public Libro {
        Objects.requireNonNull(isbn,   "ISBN no puede ser null");
        Objects.requireNonNull(titulo, "Título no puede ser null");
        Objects.requireNonNull(autor,  "Autor no puede ser null");
        Objects.requireNonNull(genero, "Género no puede ser null");
        if (isbn.isBlank())   throw new IllegalArgumentException("ISBN vacío");
        if (titulo.isBlank()) throw new IllegalArgumentException("Título vacío");
        if (autor.isBlank())  throw new IllegalArgumentException("Autor vacío");
        if (anioPublicacion < 1450 || anioPublicacion > LocalDate.now().getYear())
            throw new IllegalArgumentException("Año inválido: " + anioPublicacion);
        if (totalEjemplares < 1)
            throw new IllegalArgumentException("Debe haber al menos 1 ejemplar");
        if (ejemplaresDisponibles < 0 || ejemplaresDisponibles > totalEjemplares)
            throw new IllegalArgumentException("Ejemplares disponibles fuera de rango");
    }

    /** Factory: libro nuevo sin ID (antes de persistir en BD) */
    public static Libro nuevo(String isbn, String titulo, String autor,
                              String editorial, int anio, Genero genero, int ejemplares) {
        return new Libro(null, isbn, titulo, autor, editorial, anio,
                         genero, ejemplares, ejemplares, LocalDateTime.now());
    }

    public boolean disponible() { return ejemplaresDisponibles > 0; }

    public Libro prestar() {
        if (!disponible())
            throw new IllegalStateException("Sin ejemplares disponibles: " + isbn);
        return new Libro(id, isbn, titulo, autor, editorial, anioPublicacion,
                genero, totalEjemplares, ejemplaresDisponibles - 1, creadoEn);
    }

    public Libro devolver() {
        if (ejemplaresDisponibles >= totalEjemplares)
            throw new IllegalStateException("Todos los ejemplares ya están en biblioteca: " + isbn);
        return new Libro(id, isbn, titulo, autor, editorial, anioPublicacion,
                genero, totalEjemplares, ejemplaresDisponibles + 1, creadoEn);
    }
}
