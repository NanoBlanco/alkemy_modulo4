package com.empresa.biblioteca.servlet;

import com.empresa.biblioteca.db.DataSourceManager;
import com.empresa.biblioteca.repository.LibroRepository;
import com.empresa.biblioteca.repository.PrestamoRepository;
import com.empresa.biblioteca.service.LibroService;
import com.empresa.biblioteca.service.PrestamoService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LISTENER — Se ejecuta al iniciar y detener la aplicación web.
 *
 * Responsabilidades:
 *   - Inicializar HikariCP al arrancar Tomcat (contextInitialized)
 *   - Crear los Services y dejarlos en el ServletContext (inyección manual)
 *   - Cerrar el pool de conexiones al detener Tomcat (contextDestroyed)
 *
 * Los Servlets obtienen los Services del ServletContext, sin new().
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(AppContextListener.class);

    // Claves para almacenar los services en el ServletContext
    public static final String LIBRO_SERVICE   = "libroService";
    public static final String PRESTAMO_SERVICE = "prestamoService";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("=== Iniciando Biblioteca Digital Web ===");

        // 1. Inicializar el pool de HikariCP (crea el esquema y datos de prueba)
        DataSourceManager.inicializar();

        // 2. Crear repositorios
        LibroRepository    libroRepo    = new LibroRepository();
        PrestamoRepository prestamoRepo = new PrestamoRepository();

        // 3. Crear servicios inyectando repositorios
        LibroService    libroService    = new LibroService(libroRepo);
        PrestamoService prestamoService = new PrestamoService(prestamoRepo, libroRepo);

        // 4. Guardar en el contexto (disponibles para todos los Servlets)
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute(LIBRO_SERVICE,   libroService);
        ctx.setAttribute(PRESTAMO_SERVICE, prestamoService);

        log.info("Servicios registrados en el contexto. Aplicación lista.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("=== Deteniendo Biblioteca Digital Web ===");
        DataSourceManager.cerrar();
    }
}
