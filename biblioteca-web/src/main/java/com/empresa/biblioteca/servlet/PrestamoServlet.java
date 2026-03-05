package com.empresa.biblioteca.servlet;

import com.empresa.biblioteca.model.Prestamo;
import com.empresa.biblioteca.model.Resultado;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * CONTROLLER — Gestión de Préstamos y Devoluciones.
 *
 * Rutas:
 *   GET  /prestamos           → listar todos los préstamos
 *   GET  /prestamos/activos   → listar solo préstamos activos
 *   GET  /prestamos/nuevo     → formulario de nuevo préstamo (?libroId=)
 *   POST /prestamos/nuevo     → procesar préstamo
 *   POST /prestamos/devolver  → procesar devolución (?id=)
 */
@WebServlet(name = "PrestamoServlet", urlPatterns = "/prestamos/*")
public class PrestamoServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        switch (getAccion(req)) {
            case "nuevo"   -> mostrarFormularioPrestamo(req, resp);
            case "activos" -> listarActivos(req, resp);
            default        -> listarTodos(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        switch (getAccion(req)) {
            case "nuevo"    -> procesarPrestamo(req, resp);
            case "devolver" -> procesarDevolucion(req, resp);
            default         -> resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // ── GET handlers ────────────────────────────────────────────────────────

    private void listarTodos(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("prestamos", getPrestamoService().listarTodos());
        req.setAttribute("vista",     "todos");
        render(req, resp, "prestamos/lista");
    }

    private void listarActivos(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("prestamos", getPrestamoService().listarActivos());
        req.setAttribute("vista",     "activos");
        render(req, resp, "prestamos/lista");
    }

    private void mostrarFormularioPrestamo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long libroId = paramLong(req, "libroId");
        if (libroId == null) {
            // Mostrar selector de libro
            req.setAttribute("libros", getLibroService().listarTodos().stream()
                    .filter(l -> l.disponible()).toList());
        } else {
            req.setAttribute("libro", getLibroService().buscarPorId(libroId).orElse(null));
            req.setAttribute("libroId", libroId);
        }
        render(req, resp, "prestamos/formulario");
    }

    // ── POST handlers ────────────────────────────────────────────────────────

    private void procesarPrestamo(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long libroId = paramLong(req, "libroId");
        if (libroId == null) {
            redirectConError(req, resp, "/prestamos/nuevo", "Debe seleccionar un libro");
            return;
        }

        Resultado<Prestamo> resultado = getPrestamoService().prestar(
                libroId,
                param(req, "nombreUsuario"),
                param(req, "emailUsuario")
        );

        if (resultado.esExito()) {
            redirectConExito(req, resp, "/prestamos/activos",
                    "Préstamo registrado para " + resultado.getValor().nombreUsuario());
        } else {
            req.setAttribute("error",   resultado.getMensajeError());
            req.setAttribute("libroId", libroId);
            req.setAttribute("libro",   getLibroService().buscarPorId(libroId).orElse(null));
            render(req, resp, "prestamos/formulario");
        }
    }

    private void procesarDevolucion(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long prestamoId = paramLong(req, "id");
        if (prestamoId == null) {
            redirectConError(req, resp, "/prestamos/activos", "ID de préstamo inválido");
            return;
        }

        Resultado<Prestamo> resultado = getPrestamoService().devolver(prestamoId);

        if (resultado.esExito()) {
            redirectConExito(req, resp, "/prestamos/activos",
                    "Devolución registrada correctamente");
        } else {
            redirectConError(req, resp, "/prestamos/activos", resultado.getMensajeError());
        }
    }

    private String getAccion(HttpServletRequest req) {
        String info = req.getPathInfo();
        if (info == null || info.equals("/")) return "lista";
        return info.replaceAll("^/", "").toLowerCase();
    }
}
