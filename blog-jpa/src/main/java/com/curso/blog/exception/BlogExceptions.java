package com.curso.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepciones personalizadas del dominio Blog.
 */
public class BlogExceptions {

    // ------ 404 Not Found ------
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class RecursoNoEncontradoException extends RuntimeException {
        public RecursoNoEncontradoException(String recurso, Long id) {
            super(String.format("%s con id %d no encontrado", recurso, id));
        }
        public RecursoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    // ------ 409 Conflict ------
    @ResponseStatus(HttpStatus.CONFLICT)
    public static class RecursoDuplicadoException extends RuntimeException {
        public RecursoDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }

    // ------ 400 Bad Request ------
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class OperacionInvalidaException extends RuntimeException {
        public OperacionInvalidaException(String mensaje) {
            super(mensaje);
        }
    }
}
