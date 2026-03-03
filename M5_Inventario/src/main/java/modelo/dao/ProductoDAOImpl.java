package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import config.ConnectionPool;
import modelo.Producto;

public class ProductoDAOImpl implements ProductoDAO {

	@Override
	public List<Producto> findAll() {
		List<Producto> lista = new ArrayList<>();
		String sql = "SELECT p.*, c.nombre AS categoria FROM producto p LEFT JOIN categoria c ON p.id_categoria = c.id WHERE activo = 1 ORDER BY nombre";
		try (Connection cnx = ConnectionPool.getInstance().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				lista.add(mapear(rs));
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al listar productos ", e);
		}
		return lista;
	}

	@Override
	public Optional<Producto> findById(int id) {
		return Optional.empty();
	}

	@Override
	public boolean insert(Producto p) {
		String sql = "INSERT INTO productos (nombre, descripcion, precio, stock, stock_min, id_categoria) VALUES (?,?,?,?,?,?)";
		try(Connection cnx = ConnectionPool.getInstance().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)){
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setInt(3, p.getPrecio());
			ps.setInt(4, p.getStock());
			ps.setInt(5, p.getStockMin());
			ps.setInt(6, p.getIdCategoria());
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			throw new RuntimeException("Error al guardar producto ", e);
		}
		
	}

	@Override
	public boolean update(Producto p) {
		String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, stock_min=?, id_categoria=? WHERE id=?";
		try(Connection cnx = ConnectionPool.getInstance().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)){
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setInt(3, p.getPrecio());
			ps.setInt(4, p.getStock());
			ps.setInt(5, p.getStockMin());
			ps.setInt(6, p.getIdCategoria());
			ps.setInt(7, p.getId());
			
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			throw new RuntimeException("Error al actualizar producto ",e);
		}
	}

	@Override
	public boolean delete(int id) {
		String sql = "UPDATE productos SET activo = 0 WHERE id = ?";
		try(Connection cnx = ConnectionPool.getInstance().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)){
			ps.setInt(1, id);
			
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			throw new RuntimeException("Error al actualizar producto ",e);
		}
	}

	private Producto mapear(ResultSet rs) throws SQLException {
		Producto p = new Producto();
		p.setId(rs.getInt("id"));
		p.setNombre(rs.getString("nombre"));
		p.setDescripcion(rs.getString("descripcion"));
		p.setPrecio(rs.getInt("precio"));
		p.setStock(rs.getInt("stock"));
		return p;
	}
}
