package com.empresa.biblioteca.repository;

import com.empresa.biblioteca.db.DataSourceManager;
import com.empresa.biblioteca.exception.DatabaseException;
import com.empresa.biblioteca.model.EstadoPrestamo;
import com.empresa.biblioteca.model.Prestamo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REPOSITORY — Acceso a datos de Prestamo usando JDBC + HikariCP.
 */
public class PrestamoRepository {

    private static final Logger log = LoggerFactory.getLogger(PrestamoRepository.class);

    private static final String SQL_FIND_ALL = """
            SELECT p.id, p.libro_id, l.titulo AS libro_titulo, l.isbn AS libro_isbn,
                   p.nombre_usuario, p.email_usuario,
                   p.fecha_prestamo, p.fecha_devolucion_esperada,
                   p.fecha_devolucion_real, p.estado
            FROM prestamos p
            JOIN libros l ON l.id = p.libro_id
            ORDER BY p.fecha_prestamo DESC
            """;

    private static final String SQL_FIND_BY_ID = """
            SELECT p.id, p.libro_id, l.titulo AS libro_titulo, l.isbn AS libro_isbn,
                   p.nombre_usuario, p.email_usuario,
                   p.fecha_prestamo, p.fecha_devolucion_esperada,
                   p.fecha_devolucion_real, p.estado
            FROM prestamos p
            JOIN libros l ON l.id = p.libro_id
            WHERE p.id = ?
            """;

    private static final String SQL_FIND_ACTIVOS = """
            SELECT p.id, p.libro_id, l.titulo AS libro_titulo, l.isbn AS libro_isbn,
                   p.nombre_usuario, p.email_usuario,
                   p.fecha_prestamo, p.fecha_devolucion_esperada,
                   p.fecha_devolucion_real, p.estado
            FROM prestamos p
            JOIN libros l ON l.id = p.libro_id
            WHERE p.estado = 'ACTIVO'
            ORDER BY p.fecha_devolucion_esperada
            """;

    private static final String SQL_INSERT = """
            INSERT INTO prestamos
                (libro_id, nombre_usuario, email_usuario,
                 fecha_prestamo, fecha_devolucion_esperada, estado)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String SQL_DEVOLVER = """
            UPDATE prestamos
            SET estado = 'DEVUELTO', fecha_devolucion_real = CURRENT_TIMESTAMP
            WHERE id = ? AND estado = 'ACTIVO'
            """;

    private static final String SQL_COUNT_ACTIVOS =
            "SELECT COUNT(*) FROM prestamos WHERE estado = 'ACTIVO'";

    // ── Operaciones ──────────────────────────────────────────────────────────

    public List<Prestamo> findAll() {
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) prestamos.add(mapear(rs));
            log.debug("findAll prestamos → {}", prestamos.size());
        } catch (SQLException e) {
            throw new DatabaseException("Error listando préstamos", e);
        }
        return prestamos;
    }

    public Optional<Prestamo> findById(Long id) {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error buscando préstamo id=" + id, e);
        }
    }

    public List<Prestamo> findActivos() {
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_ACTIVOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) prestamos.add(mapear(rs));
        } catch (SQLException e) {
            throw new DatabaseException("Error listando préstamos activos", e);
        }
        return prestamos;
    }

    /**
     * Registra un nuevo préstamo y descuenta el ejemplar — dentro de una transacción.
     * La transacción garantiza que si cualquier operación falla,
     * ambas (INSERT prestamo + UPDATE ejemplares) se revierten juntas.
     */
    public Prestamo guardar(Prestamo prestamo, LibroRepository libroRepo, int nuevosEjemplares) {
        Connection con = null;
        try {
            con = DataSourceManager.getConnection();
            con.setAutoCommit(false); // ← inicio de transacción explícita

            // 1. Insertar el préstamo
            try (PreparedStatement ps = con.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1,      prestamo.libroId());
                ps.setString(2,    prestamo.nombreUsuario());
                ps.setString(3,    prestamo.emailUsuario());
                ps.setTimestamp(4, Timestamp.valueOf(prestamo.fechaPrestamo()));
                ps.setTimestamp(5, Timestamp.valueOf(prestamo.fechaDevolucionEsperada()));
                ps.setString(6,    prestamo.estado().name());
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) throw new DatabaseException("No se generó ID para préstamo");

                    // 2. Actualizar ejemplares disponibles en el mismo connection/transacción
                    try (PreparedStatement upd = con.prepareStatement(
                            "UPDATE libros SET ejemplares_disponibles = ? WHERE id = ?")) {
                        upd.setInt(1, nuevosEjemplares);
                        upd.setLong(2, prestamo.libroId());
                        upd.executeUpdate();
                    }

                    con.commit(); // ← COMMIT: ambas operaciones exitosas

                    long id = keys.getLong(1);
                    log.info("Préstamo registrado id={} libro={} usuario={}",
                            id, prestamo.libroId(), prestamo.nombreUsuario());
                    return findById(id).orElseThrow();
                }
            }
        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); log.warn("ROLLBACK préstamo libro={}", prestamo.libroId()); }
                catch (SQLException ex) { log.error("Error en rollback", ex); }
            }
            throw new DatabaseException("Error registrando préstamo", e);
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); } // devuelve al pool
                catch (SQLException e) { log.error("Error cerrando conexión", e); }
            }
        }
    }

    /**
     * Devuelve un préstamo y repone el ejemplar — dentro de una transacción.
     */
    public Prestamo devolver(Long prestamoId, LibroRepository libroRepo) {
        Connection con = null;
        try {
            con = DataSourceManager.getConnection();
            con.setAutoCommit(false);

            // 1. Marcar el préstamo como DEVUELTO
            try (PreparedStatement ps = con.prepareStatement(SQL_DEVOLVER)) {
                ps.setLong(1, prestamoId);
                int rows = ps.executeUpdate();
                if (rows == 0) throw new DatabaseException("Préstamo no activo o no encontrado: " + prestamoId);
            }

            // 2. Recuperar el préstamo para saber qué libro reponer
            Prestamo p = findById(prestamoId, con);

            // 3. Reponer el ejemplar
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE libros SET ejemplares_disponibles = ejemplares_disponibles + 1 WHERE id = ?")) {
                ps.setLong(1, p.libroId());
                ps.executeUpdate();
            }

            con.commit();
            log.info("Préstamo devuelto id={} libro={}", prestamoId, p.libroId());
            return findById(prestamoId).orElseThrow();

        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { log.error("Error rollback", ex); }
            }
            throw new DatabaseException("Error devolviendo préstamo id=" + prestamoId, e);
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); }
                catch (SQLException e) { log.error("Error cerrando conexión", e); }
            }
        }
    }

    public long contarActivos() {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_COUNT_ACTIVOS);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getLong(1) : 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error contando préstamos activos", e);
        }
    }

    // ── Mapeo ────────────────────────────────────────────────────────────────

    private Prestamo mapear(ResultSet rs) throws SQLException {
        Timestamp devReal = rs.getTimestamp("fecha_devolucion_real");
        return new Prestamo(
                rs.getLong("id"),
                rs.getLong("libro_id"),
                rs.getString("libro_titulo"),
                rs.getString("libro_isbn"),
                rs.getString("nombre_usuario"),
                rs.getString("email_usuario"),
                rs.getTimestamp("fecha_prestamo").toLocalDateTime(),
                rs.getTimestamp("fecha_devolucion_esperada").toLocalDateTime(),
                devReal != null ? devReal.toLocalDateTime() : null,
                EstadoPrestamo.valueOf(rs.getString("estado"))
        );
    }

    /** Sobrecarga interna que reutiliza una conexión existente (dentro de transacción) */
    private Prestamo findById(Long id, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
                throw new DatabaseException("Préstamo no encontrado id=" + id);
            }
        }
    }
}
