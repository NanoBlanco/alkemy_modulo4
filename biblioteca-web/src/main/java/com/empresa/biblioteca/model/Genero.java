package com.empresa.biblioteca.model;

public enum Genero {
    FICCION("Ficción"),
    NO_FICCION("No Ficción"),
    CIENCIA("Ciencia"),
    HISTORIA("Historia"),
    TECNOLOGIA("Tecnología"),
    INFANTIL("Infantil"),
    BIOGRAFIA("Biografía"),
    ARTE("Arte");

    private final String etiqueta;

    Genero(String etiqueta) { this.etiqueta = etiqueta; }

    public String getEtiqueta() { return etiqueta; }
}
