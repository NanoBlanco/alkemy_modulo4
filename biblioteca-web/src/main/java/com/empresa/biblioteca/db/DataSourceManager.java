package com.empresa.biblioteca.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DB — Gestiona el pool de conexiones HikariCP.
 *
 * HikariCP es el connection pool más rápido para Java:
 *   - Reutiliza conexiones en lugar de crear/cerrar una por request
 *   - Configurable: tamaño del pool, timeout, validación de conexiones
 *   - Thread-safe: varios Servlets pueden pedir conexiones simultáneamente
 *
 * Patrón Singleton: una sola instancia del pool por aplicación.
 */
public final class DataSourceManager {

    private static final Logger log = LoggerFactory.getLogger(DataSourceManager.class);

    // volatile garantiza visibilidad entre hilos (Java Memory Model)
    private static volatile HikariDataSource dataSource;

    private DataSourceManager() {} // no instanciable

    /**
     * Inicializa el pool HikariCP leyendo hikari.properties del classpath.
     * Llamado una sola vez desde el AppListener al arrancar Tomcat.
     */
    public static void inicializar() {
        if (dataSource != null && !dataSource.isClosed()) {
            log.warn("DataSource ya inicializado, ignorando segunda llamada");
            return;
        }

        try (InputStream is = DataSourceManager.class
                .getClassLoader().getResourceAsStream("hikari.properties")) {

            if (is == null)
                throw new IllegalStateException("No se encontró hikari.properties en classpath");

            Properties props = new Properties();
            props.load(is);

            HikariConfig config = new HikariConfig(props);

            // Configuraciones adicionales por código (complementan hikari.properties)
            config.addDataSourceProperty("cachePrepStmts",          "true");
            config.addDataSourceProperty("prepStmtCacheSize",        "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit",    "2048");
            config.addDataSourceProperty("useServerPrepStmts",       "true");

            dataSource = new HikariDataSource(config);

            log.info("Pool HikariCP inicializado — jdbcUrl: {}, poolSize: {}",
                    config.getJdbcUrl(), config.getMaximumPoolSize());

            crearEsquema();
            insercionDatosPrueba();

        } catch (IOException e) {
            throw new IllegalStateException("Error leyendo hikari.properties", e);
        }
    }

    /**
     * Obtiene una conexión del pool.
     * IMPORTANTE: siempre usar en try-with-resources para devolver al pool.
     *
     *   try (Connection con = DataSourceManager.getConnection()) { ... }
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null || dataSource.isClosed())
            throw new IllegalStateException("DataSource no inicializado. Llamar inicializar() primero.");
        return dataSource.getConnection();
    }

    /**
     * Cierra el pool de conexiones.
     * Llamado desde AppListener al detener Tomcat.
     */
    public static void cerrar() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            log.info("Pool HikariCP cerrado correctamente");
        }
    }

    // ─── Schema DDL ────────────────────────────────────────────────────────────

    private static void crearEsquema() {
        String sqlLibros = """
            CREATE TABLE IF NOT EXISTS libros (
                id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
                isbn                    VARCHAR(20)  NOT NULL UNIQUE,
                titulo                  VARCHAR(200) NOT NULL,
                autor                   VARCHAR(150) NOT NULL,
                editorial               VARCHAR(100),
                anio_publicacion        INT          NOT NULL,
                genero                  VARCHAR(50)  NOT NULL,
                total_ejemplares        INT          NOT NULL DEFAULT 1,
                ejemplares_disponibles  INT          NOT NULL DEFAULT 1,
                creado_en               TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                CONSTRAINT chk_ejemplares CHECK (ejemplares_disponibles >= 0
                    AND ejemplares_disponibles <= total_ejemplares)
            )
            """;

        String sqlPrestamos = """
            CREATE TABLE IF NOT EXISTS prestamos (
                id                        BIGINT AUTO_INCREMENT PRIMARY KEY,
                libro_id                  BIGINT       NOT NULL,
                nombre_usuario            VARCHAR(100) NOT NULL,
                email_usuario             VARCHAR(150) NOT NULL,
                fecha_prestamo            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                fecha_devolucion_esperada TIMESTAMP    NOT NULL,
                fecha_devolucion_real     TIMESTAMP,
                estado                    VARCHAR(20)  NOT NULL DEFAULT 'ACTIVO',
                CONSTRAINT fk_prestamos_libros FOREIGN KEY (libro_id)
                    REFERENCES libros(id) ON DELETE RESTRICT,
                CONSTRAINT chk_estado CHECK (estado IN ('ACTIVO','DEVUELTO','VENCIDO'))
            )
            """;

        try (Connection con = getConnection();
             var st = con.createStatement()) {
            st.execute(sqlLibros);
            st.execute(sqlPrestamos);
            log.info("Esquema de base de datos verificado/creado");
        } catch (SQLException e) {
            throw new IllegalStateException("Error creando esquema", e);
        }
    }

    private static void insercionDatosPrueba() {
        String check = "SELECT COUNT(*) FROM libros";
        try (Connection con = getConnection();
             var st = con.createStatement();
             var rs = st.executeQuery(check)) {
            rs.next();
            if (rs.getInt(1) > 0) return; // ya tiene datos
        } catch (SQLException e) {
            log.warn("No se pudo verificar datos existentes: {}", e.getMessage());
            return;
        }

        String sql = """
            INSERT INTO libros
                (isbn, titulo, autor, editorial, anio_publicacion,
                 genero, total_ejemplares, ejemplares_disponibles)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        Object[][] libros = {
            {"978-84-376-0494-7", "Cien años de soledad",      "Gabriel García Márquez", "Sudamericana",   1967, "FICCION",    3, 3},
            {"978-0-13-468599-1", "Clean Code",                 "Robert C. Martin",       "Prentice Hall",  2008, "TECNOLOGIA", 4, 4},
            {"978-0-201-63361-0", "Design Patterns",            "Gang of Four",           "Addison-Wesley", 1994, "TECNOLOGIA", 2, 2},
            {"978-84-663-1900-0", "El nombre de la rosa",       "Umberto Eco",            "Lumen",          1980, "FICCION",    2, 2},
            {"978-0-13-110362-7", "The C Programming Language", "Kernighan & Ritchie",    "Prentice Hall",  1988, "TECNOLOGIA", 3, 3},
            {"978-84-320-6327-3", "Sapiens",                    "Yuval Noah Harari",      "Debate",         2011, "HISTORIA",   3, 3},
            {"978-0-06-112008-4", "To Kill a Mockingbird",      "Harper Lee",             "Harper Perennial",1960,"FICCION",    2, 2},
            {"978-84-9838-753-0", "Una breve historia del tiempo","Stephen Hawking",      "Crítica",        1988, "CIENCIA",    2, 2},
        };

        try (Connection con = getConnection();
             var ps = con.prepareStatement(sql)) {
            for (Object[] l : libros) {
                ps.setString(1, (String) l[0]);
                ps.setString(2, (String) l[1]);
                ps.setString(3, (String) l[2]);
                ps.setString(4, (String) l[3]);
                ps.setInt(5,    (int)    l[4]);
                ps.setString(6, (String) l[5]);
                ps.setInt(7,    (int)    l[6]);
                ps.setInt(8,    (int)    l[7]);
                ps.addBatch();
            }
            ps.executeBatch();
            log.info("Datos de prueba insertados: {} libros", libros.length);
        } catch (SQLException e) {
            log.warn("Error insertando datos de prueba (pueden ya existir): {}", e.getMessage());
        }
    }
}
