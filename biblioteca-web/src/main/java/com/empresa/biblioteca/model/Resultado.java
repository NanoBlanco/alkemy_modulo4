package com.empresa.biblioteca.model;

/**
 * Sealed interface para representar el resultado de una operación.
 * Evita el uso de excepciones para flujo de control normal.
 * Java 17: solo Exito y Error pueden implementar esta interfaz.
 */
public sealed interface Resultado<T>
        permits Resultado.Exito, Resultado.Error {

    record Exito<T>(T valor)        implements Resultado<T> {}
    record Error<T>(String mensaje) implements Resultado<T> {}

    static <T> Resultado<T> ok(T valor)        { return new Exito<>(valor); }
    static <T> Resultado<T> error(String msg)  { return new Error<>(msg); }

    default boolean esExito() { return this instanceof Exito<T>; }
    default boolean esError() { return this instanceof Error<T>; }

    default T getValor() {
        if (this instanceof Exito<T> e) return e.valor();
        throw new IllegalStateException("El resultado es un error, no tiene valor");
    }

    default String getMensajeError() {
        if (this instanceof Error<T> e) return e.mensaje();
        throw new IllegalStateException("El resultado es exitoso, no tiene mensaje de error");
    }
}
