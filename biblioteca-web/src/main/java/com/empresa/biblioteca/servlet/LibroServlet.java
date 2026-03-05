package com.empresa.biblioteca.servlet;

import com.empresa.biblioteca.model.Genero;
import com.empresa.biblioteca.model.Libro;
import com.empresa.biblioteca.model.Resultado;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

/**
 * CONTROLLER — CRUD completo de Libros bajo patrón MVC.
 *
 * Rutas:
 *   GET  /libros              → listar todos (con búsqueda opcional ?q=)
 *   GET  /libros/nuevo        → mostrar formulario de creación
 *   POST /libros/nuevo        → procesar creación
 *   GET  /libros/editar?id=   → mostrar formulario de edición
 *   POST /libros/editar       → procesar edición
 *   POST /libros/eliminar     → eliminar libro
 *   GET  /libros/detalle?id=  → detalle de un libro
 */
@WebServlet(name = "LibroServlet", urlPatterns = "/libros/*")
public class LibroServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = getAccion(req);

        switch (accion) {
            case "nuevo"   -> mostrarFormularioNuevo(req, resp);
            case "editar"  -> mostrarFormularioEditar(req, resp);
            case "detalle" -> mostrarDetalle(req, resp);
            default        -> listar(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = getAccion(req);

        switch (accion) {
            case "nuevo"    -> procesarNuevo(req, resp);
            case "editar"   -> procesarEditar(req, resp);
            case "eliminar" -> procesarEliminar(req, resp);
            default         -> resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción desconocida");
        }
    }

    // ── GET handlers ────────────────────────────────────────────────────────

    private void listar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String q = param(req, "q");
        var libros = q.isBlank()
                ? getLibroService().listarTodos()
                : getLibroService().buscar(q);

        req.setAttribute("libros",  libros);
        req.setAttribute("q",       q);
        req.setAttribute("generos", Genero.values());
        render(req, resp, "libros/lista");
    }

    private void mostrarFormularioNuevo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("generos", Genero.values());
        req.setAttribute("accion",  "nuevo");
        render(req, resp, "libros/formulario");
    }

    private void mostrarFormularioEditar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = paramLong(req, "id");
        if (id == null) { resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID requerido"); return; }

        Optional<Libro> libro = getLibroService().buscarPorId(id);
        if (libro.isEmpty()) { resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Libro no encontrado"); return; }

        req.setAttribute("libro",   libro.get());
        req.setAttribute("generos", Genero.values());
        req.setAttribute("accion",  "editar");
        render(req, resp, "libros/formulario");
    }

    private void mostrarDetalle(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = paramLong(req, "id");
        if (id == null) { resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID requerido"); return; }

        Optional<Libro> libro = getLibroService().buscarPorId(id);
        if (libro.isEmpty()) { resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Libro no encontrado"); return; }

        req.setAttribute("libro",    libro.get());
        req.setAttribute("prestamosActivos", getPrestamoService().listarActivos().stream()
                .filter(p -> p.libroId().equals(id)).toList());
        render(req, resp, "libros/detalle");
    }

    // ── POST handlers ────────────────────────────────────────────────────────

    private void procesarNuevo(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Resultado<Libro> resultado = getLibroService().registrar(
                param(req, "isbn"),
                param(req, "titulo"),
                param(req, "autor"),
                param(req, "editorial"),
                paramInt(req, "anioPublicacion", 2024),
                param(req, "genero"),
                paramInt(req, "totalEjemplares", 1)
        );

        if (resultado.esExito()) {
            redirectConExito(req, resp, "/libros",
                    "Libro '" + resultado.getValor().titulo() + "' registrado correctamente");
        } else {
            req.setAttribute("error",   resultado.getMensajeError());
            req.setAttribute("generos", Genero.values());
            req.setAttribute("accion",  "nuevo");
            // Repoblar el formulario con los valores enviados
            req.setAttribute("libroForm", buildFormBean(req));
            render(req, resp, "libros/formulario");
        }
    }

    private void procesarEditar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = paramLong(req, "id");
        if (id == null) { resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID requerido"); return; }

        Resultado<Libro> resultado = getLibroService().actualizar(
                id,
                param(req, "isbn"),
                param(req, "titulo"),
                param(req, "autor"),
                param(req, "editorial"),
                paramInt(req, "anioPublicacion", 2024),
                param(req, "genero"),
                paramInt(req, "totalEjemplares", 1)
        );

        if (resultado.esExito()) {
            redirectConExito(req, resp, "/libros",
                    "Libro actualizado correctamente");
        } else {
            req.setAttribute("error",   resultado.getMensajeError());
            req.setAttribute("generos", Genero.values());
            req.setAttribute("accion",  "editar");
            req.setAttribute("libroForm", buildFormBean(req));
            render(req, resp, "libros/formulario");
        }
    }

    private void procesarEliminar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = paramLong(req, "id");
        if (id == null) { redirectConError(req, resp, "/libros", "ID inválido"); return; }

        Resultado<Void> resultado = getLibroService().eliminar(id);
        if (resultado.esExito()) {
            redirectConExito(req, resp, "/libros", "Libro eliminado correctamente");
        } else {
            redirectConError(req, resp, "/libros", resultado.getMensajeError());
        }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    /** Extrae la sub-ruta: /libros/editar → "editar" */
    private String getAccion(HttpServletRequest req) {
        String info = req.getPathInfo();
        if (info == null || info.equals("/")) return "lista";
        return info.replaceAll("^/", "").toLowerCase();
    }

    /** DTO simple para repoblar el formulario si hay errores */
    private record FormBean(String isbn, String titulo, String autor, String editorial,
                             String anioPublicacion, String genero, String totalEjemplares) {}

    private FormBean buildFormBean(HttpServletRequest req) {
        return new FormBean(
                param(req, "isbn"), param(req, "titulo"), param(req, "autor"),
                param(req, "editorial"), param(req, "anioPublicacion"),
                param(req, "genero"), param(req, "totalEjemplares"));
    }
}
