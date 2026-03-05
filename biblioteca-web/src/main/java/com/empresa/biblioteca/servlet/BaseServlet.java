package com.empresa.biblioteca.servlet;

import com.empresa.biblioteca.service.LibroService;
import com.empresa.biblioteca.service.PrestamoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SERVLET BASE — Funcionalidad compartida para todos los controladores.
 *
 * Proporciona:
 *   - Acceso a los Services desde el contexto
 *   - Helpers para renderizar vistas JSP
 *   - Helpers para respuestas JSON (API)
 *   - Helpers para parsing de parámetros
 *   - Manejo de errores centralizado
 */
public abstract class BaseServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(BaseServlet.class);

    protected static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                (com.google.gson.JsonSerializer<LocalDateTime>) (src, type, ctx) ->
                    new com.google.gson.JsonPrimitive(
                        src.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))))
            .create();

    // ── Acceso a Services desde el contexto ─────────────────────────────────

    protected LibroService getLibroService() {
        return (LibroService) getServletContext()
                .getAttribute(AppContextListener.LIBRO_SERVICE);
    }

    protected PrestamoService getPrestamoService() {
        return (PrestamoService) getServletContext()
                .getAttribute(AppContextListener.PRESTAMO_SERVICE);
    }

    // ── Renderizado de vistas ────────────────────────────────────────────────

    protected void render(HttpServletRequest req, HttpServletResponse resp,
                          String vista) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/WEB-INF/views/" + vista + ".jsp")
           .forward(req, resp);
    }

    // ── Respuestas JSON (para endpoints AJAX) ────────────────────────────────

    protected void jsonOk(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(GSON.toJson(data));
    }

    protected void jsonError(HttpServletResponse resp, int status,
                             String mensaje) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(status);
        resp.getWriter().write(GSON.toJson(new ErrorResponse(mensaje)));
    }

    // ── Parsing de parámetros ────────────────────────────────────────────────

    protected String param(HttpServletRequest req, String nombre) {
        String val = req.getParameter(nombre);
        return (val != null) ? val.trim() : "";
    }

    protected Long paramLong(HttpServletRequest req, String nombre) {
        try {
            return Long.parseLong(param(req, nombre));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected int paramInt(HttpServletRequest req, String nombre, int defaultVal) {
        try {
            return Integer.parseInt(param(req, nombre));
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    // ── Redirección con mensaje flash ────────────────────────────────────────

    protected void redirectConExito(HttpServletRequest req, HttpServletResponse resp,
                                    String url, String mensaje) throws IOException {
        req.getSession().setAttribute("flashExito", mensaje);
        resp.sendRedirect(req.getContextPath() + url);
    }

    protected void redirectConError(HttpServletRequest req, HttpServletResponse resp,
                                    String url, String mensaje) throws IOException {
        req.getSession().setAttribute("flashError", mensaje);
        resp.sendRedirect(req.getContextPath() + url);
    }

    // ── Manejo de errores ────────────────────────────────────────────────────

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Exception e) {
            log.error("Error no manejado en {}: {}", getClass().getSimpleName(), e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                           "Error interno: " + e.getMessage());
        }
    }

    // ── DTO interno para errores JSON ────────────────────────────────────────
    private record ErrorResponse(String error) {}
}
