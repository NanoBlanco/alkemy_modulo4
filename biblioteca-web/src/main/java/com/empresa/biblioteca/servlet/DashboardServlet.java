package com.empresa.biblioteca.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * CONTROLLER — Dashboard principal.
 * Ruta: GET /
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"", "/", "/dashboard"})
public class DashboardServlet extends BaseServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("totalLibros",       getLibroService().contarTotal());
        req.setAttribute("librosDisponibles", getLibroService().contarDisponibles());
        req.setAttribute("prestamosActivos",  getPrestamoService().contarActivos());
        req.setAttribute("ultimosPrestamos",  getPrestamoService().listarActivos());

        render(req, resp, "dashboard");
    }
}
