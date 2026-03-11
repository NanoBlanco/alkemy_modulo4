package com.curso.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ============================================================
 *  Blog JPA — Ejercicio Integrador
 *  Arquitectura de Capas:
 *
 *  [ Controller ] → recibe HTTP, delega al Service
 *       ↓
 *   [ Service ]   → lógica de negocio, transacciones
 *       ↓
 *  [ Repository ] → acceso a datos JPA/Hibernate
 *       ↓
 *   [ Entity ]    → mapeo objeto-relacional
 *       ↓
 *  [ MySQL BD ]   → base de datos: 'blog'
 * ============================================================
 */
@SpringBootApplication
public class BlogJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogJpaApplication.class, args);
    }
}
