package com.empresa.biblioteca.exception;

/**
 * Excepción no verificada para errores de acceso a datos.
 * Envuelve SQLExceptions para no contaminar las capas superiores con JDBC.
 */
public class DatabaseException extends RuntimeException {
    public DatabaseException(String mensaje) {
        super(mensaje);
    }
    public DatabaseException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
