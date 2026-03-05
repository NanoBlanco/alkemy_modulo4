package com.empresa.biblioteca.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * MODEL — Record inmutable que representa un Préstamo de libro.
 */
public record Prestamo(
        Long          id,
        Long          libroId,
        String        libroTitulo,
        String        libroIsbn,
        String        nombreUsuario,
        String        emailUsuario,
        LocalDateTime fechaPrestamo,
        LocalDateTime fechaDevolucionEsperada,
        LocalDateTime fechaDevolucionReal,
        EstadoPrestamo estado
) {
    public Prestamo {
        Objects.requireNonNull(libroId,       "libroId no puede ser null");
        Objects.requireNonNull(nombreUsuario, "nombreUsuario no puede ser null");
        Objects.requireNonNull(emailUsuario,  "emailUsuario no puede ser null");
        Objects.requireNonNull(fechaPrestamo, "fechaPrestamo no puede ser null");
        Objects.requireNonNull(estado,        "estado no puede ser null");
        if (nombreUsuario.isBlank()) throw new IllegalArgumentException("Nombre de usuario vacío");
        if (emailUsuario.isBlank())  throw new IllegalArgumentException("Email vacío");
    }

    /** Factory: nuevo préstamo activo por 15 días */
    public static Prestamo nuevo(Long libroId, String libroTitulo, String libroIsbn,
                                  String nombre, String email) {
        LocalDateTime ahora = LocalDateTime.now();
        return new Prestamo(null, libroId, libroTitulo, libroIsbn,
                nombre, email, ahora, ahora.plusDays(15),
                null, EstadoPrestamo.ACTIVO);
    }

    public boolean estaActivo()  { return estado == EstadoPrestamo.ACTIVO; }
    public boolean estaVencido() {
        return estado == EstadoPrestamo.ACTIVO
            && LocalDateTime.now().isAfter(fechaDevolucionEsperada);
    }

    public Prestamo devolver() {
        return new Prestamo(id, libroId, libroTitulo, libroIsbn,
                nombreUsuario, emailUsuario, fechaPrestamo,
                fechaDevolucionEsperada, LocalDateTime.now(), EstadoPrestamo.DEVUELTO);
    }
}
