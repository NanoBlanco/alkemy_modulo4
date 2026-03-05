package com.empresa.biblioteca.repository;

import com.empresa.biblioteca.db.DataSourceManager;
import com.empresa.biblioteca.exception.DatabaseException;
import com.empresa.biblioteca.model.Genero;
import com.empresa.biblioteca.model.Libro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REPOSITORY — Acceso a datos de Libro usando JDBC + HikariCP.
 *
 * Responsabilidades de esta capa:
 *   - Traducir objetos Java ↔ filas SQL
 *   - Gestionar conexiones del pool (siempre en try-with-resources)
 *   - Envolver SQLExceptions en DatabaseException (sin checked exceptions en capas superiores)
 *
 * NO contiene lógica de negocio. NO conoce los Servlets.
 */
public class LibroRepository {

    private static final Logger log = LoggerFactory.getLogger(LibroRepository.class);

    // ── SQL Constants ────────────────────────────────────────────────────────
    private static final String SQL_FIND_ALL = """
            SELECT id, isbn, titulo, autor, editorial, anio_publicacion,
                   genero, total_ejemplares, ejemplares_disponibles, creado_en
            FROM libros
            ORDER BY titulo
            """;

    private static final String SQL_FIND_BY_ID = """
            SELECT id, isbn, titulo, autor, editorial, anio_publicacion,
                   genero, total_ejemplares, ejemplares_disponibles, creado_en
            FROM libros WHERE id = ?
            """;

    private static final String SQL_FIND_BY_ISBN = """
            SELECT id, isbn, titulo, autor, editorial, anio_publicacion,
                   genero, total_ejemplares, ejemplares_disponibles, creado_en
            FROM libros WHERE isbn = ?
            """;

    private static final String SQL_BUSCAR = """
            SELECT id, isbn, titulo, autor, editorial, anio_publicacion,
                   genero, total_ejemplares, ejemplares_disponibles, creado_en
            FROM libros
            WHERE LOWER(titulo) LIKE ? OR LOWER(autor) LIKE ? OR LOWER(isbn) LIKE ?
            ORDER BY titulo
            """;

    private static final String SQL_INSERT = """
            INSERT INTO libros
                (isbn, titulo, autor, editorial, anio_publicacion,
                 genero, total_ejemplares, ejemplares_disponibles)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String SQL_UPDATE = """
            UPDATE libros SET
                isbn = ?, titulo = ?, autor = ?, editorial = ?,
                anio_publicacion = ?, genero = ?,
                total_ejemplares = ?, ejemplares_disponibles = ?
            WHERE id = ?
            """;

    private static final String SQL_UPDATE_EJEMPLARES = """
            UPDATE libros SET ejemplares_disponibles = ? WHERE id = ?
            """;

    private static final String SQL_DELETE = "DELETE FROM libros WHERE id = ?";

    private static final String SQL_COUNT = "SELECT COUNT(*) FROM libros";

    private static final String SQL_COUNT_DISPONIBLES =
            "SELECT COUNT(*) FROM libros WHERE ejemplares_disponibles > 0";

    // ── Operaciones CRUD ─────────────────────────────────────────────────────

    public List<Libro> findAll() {
        List<Libro> libros = new ArrayList<>();
        // try-with-resources: devuelve la conexión al pool de HikariCP automáticamente
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) libros.add(mapear(rs));
            log.debug("findAll() → {} libros", libros.size());

        } catch (SQLException e) {
            throw new DatabaseException("Error listando libros", e);
        }
        return libros;
    }

    public Optional<Libro> findById(Long id) {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error buscando libro id=" + id, e);
        }
    }

    public Optional<Libro> findByIsbn(String isbn) {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ISBN)) {

            ps.setString(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error buscando libro isbn=" + isbn, e);
        }
    }

    public List<Libro> buscar(String termino) {
        String like = "%" + termino.toLowerCase() + "%";
        List<Libro> libros = new ArrayList<>();
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR)) {

            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) libros.add(mapear(rs));
            }
            log.debug("buscar('{}') → {} resultados", termino, libros.size());
        } catch (SQLException e) {
            throw new DatabaseException("Error buscando libros con término: " + termino, e);
        }
        return libros;
    }

    public Libro guardar(Libro libro) {
        if (libro.id() == null) return insertar(libro);
        else                    return actualizar(libro);
    }

    private Libro insertar(Libro libro) {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT,
                     Statement.RETURN_GENERATED_KEYS)) {

            mapearParametros(ps, libro);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);
                    log.info("Libro insertado id={} isbn={}", id, libro.isbn());
                    return findById(id).orElseThrow();
                }
            }
            throw new DatabaseException("No se obtuvo ID generado para libro " + libro.isbn());

        } catch (SQLException e) {
            throw new DatabaseException("Error insertando libro isbn=" + libro.isbn(), e);
        }
    }

    private Libro actualizar(Libro libro) {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            mapearParametros(ps, libro);
            ps.setLong(9, libro.id());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DatabaseException("Libro no encontrado id=" + libro.id());
            log.info("Libro actualizado id={}", libro.id());
            return findById(libro.id()).orElseThrow();

        } catch (SQLException e) {
            throw new DatabaseException("Error actualizando libro id=" + libro.id(), e);
        }
    }

    /** Actualiza solo el contador de ejemplares disponibles (para préstamos/devoluciones) */
    public void actualizarEjemplares(Long libroId, int nuevosEjemplares) {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE_EJEMPLARES)) {

            ps.setInt(1, nuevosEjemplares);
            ps.setLong(2, libroId);
            ps.executeUpdate();
            log.debug("Ejemplares libro id={} → {}", libroId, nuevosEjemplares);

        } catch (SQLException e) {
            throw new DatabaseException("Error actualizando ejemplares libro id=" + libroId, e);
        }
    }

    public boolean eliminar(Long id) {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            log.info("Libro eliminado id={}, filas afectadas={}", id, rows);
            return rows > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error eliminando libro id=" + id, e);
        }
    }

    public long contarTotal() {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_COUNT);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getLong(1) : 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error contando libros", e);
        }
    }

    public long contarDisponibles() {
        try (Connection con = DataSourceManager.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_COUNT_DISPONIBLES);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getLong(1) : 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error contando libros disponibles", e);
        }
    }

    // ── Mapeo ResultSet → Record ─────────────────────────────────────────────

    private Libro mapear(ResultSet rs) throws SQLException {
        return new Libro(
                rs.getLong("id"),
                rs.getString("isbn"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("editorial"),
                rs.getInt("anio_publicacion"),
                Genero.valueOf(rs.getString("genero")),
                rs.getInt("total_ejemplares"),
                rs.getInt("ejemplares_disponibles"),
                rs.getTimestamp("creado_en").toLocalDateTime()
        );
    }

    // ── Mapeo Record → PreparedStatement (para INSERT y UPDATE) ─────────────

    private void mapearParametros(PreparedStatement ps, Libro l) throws SQLException {
        ps.setString(1, l.isbn());
        ps.setString(2, l.titulo());
        ps.setString(3, l.autor());
        ps.setString(4, l.editorial() != null ? l.editorial() : "");
        ps.setInt(5,    l.anioPublicacion());
        ps.setString(6, l.genero().name());
        ps.setInt(7,    l.totalEjemplares());
        ps.setInt(8,    l.ejemplaresDisponibles());
    }
}
