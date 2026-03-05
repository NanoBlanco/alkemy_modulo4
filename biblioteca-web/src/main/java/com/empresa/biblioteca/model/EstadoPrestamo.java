package com.empresa.biblioteca.model;

public enum EstadoPrestamo {
    ACTIVO("Activo"),
    DEVUELTO("Devuelto"),
    VENCIDO("Vencido");

    private final String etiqueta;
    EstadoPrestamo(String e) { this.etiqueta = e; }
    public String getEtiqueta() { return etiqueta; }
}
