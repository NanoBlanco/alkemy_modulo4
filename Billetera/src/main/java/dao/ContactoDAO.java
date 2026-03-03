package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import interfaces.IContactoDAO;
import modelos.Contacto;

public class ContactoDAO implements IContactoDAO {

	
	public ContactoDAO() {}
		
	@Override
	public Contacto buscarPorId(Integer id) {
		try (Connection cnx = DBConnection.getConnection(); 
				PreparedStatement ps = cnx.prepareStatement("SELECT id, nombre, correo FROM contactos WHERE id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return mapear(rs);
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al buscar contacto por Id ", e);
		}
		return null;
	}

	@Override
	public Contacto buscarContacto(String correo) {
		try (Connection cnx = DBConnection.getConnection(); 
				PreparedStatement ps = cnx.prepareStatement("SELECT id, nombre, correo FROM contactos WHERE correo = ?")) {
			ps.setString(1, correo);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return mapear(rs);
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al buscar contacto por correo ", e);
		}
		return null;
	}

	@Override
	public List<Contacto> listar() {
		List<Contacto> lista = new ArrayList<>();
		try (Connection cnx = DBConnection.getConnection(); 
				PreparedStatement ps = cnx.prepareStatement("SELECT id, nombre, correo FROM contactos");
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				lista.add(mapear(rs));
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al listar contactos", e);
		}
		return lista;
	}

	@Override
	public void guardar(Contacto c) {
		try (Connection cnx = DBConnection.getConnection(); 
				PreparedStatement ps = cnx.prepareStatement("INSERT INTO contactos (nombre, correo) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getCorreo());
			ps.executeUpdate() ;
			try (ResultSet keys = ps.getGeneratedKeys()){
				if(keys.next()) {
					c.setId(keys.getInt(1));
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al guardar contacto ", e);
		}
	}

	@Override
	public void actualizar(Contacto entidad) {
		try (Connection cnx = DBConnection.getConnection(); 
				PreparedStatement ps = cnx.prepareStatement("UPDATE contactos SET nombre = ?, correo = ? WHERE id = ?")) {
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getCorreo());
			ps.setInt(3, entidad.getId());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al actualizar contacto ", e);
		}
	}

	@Override
	public void eliminar(Integer id) {
		try (Connection cnx = DBConnection.getConnection(); 
				PreparedStatement ps = cnx.prepareStatement("DELETE FROM contactos WHERE id = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException("Error al eliminar contacto ", e);
		}		
	}

	
	private Contacto mapear(ResultSet rs) throws SQLException {
		return new Contacto(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("correo")
				);
	}

}
