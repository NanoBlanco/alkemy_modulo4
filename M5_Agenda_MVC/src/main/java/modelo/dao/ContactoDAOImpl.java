package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import modelo.ContactoDTO;

public class ContactoDAOImpl implements ContactoDAO {
	
	private Connection cnx;
	
	public ContactoDAOImpl() {
		cnx = DBConnection.getInstance().getConnection();
	}

	@Override
	public List<ContactoDTO> listar() {
		List<ContactoDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM contactos";
		try (PreparedStatement ps = cnx.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				lista.add(mapear(rs));
			}
		}catch(SQLException e) {
			System.out.println("Error al listar: "+e.getMessage());
		}
		return lista;
	}

	@Override
	public void guardar(ContactoDTO dto) {
		String sql = "INSERT INTO contactos (nombre, correo, telefono) VALUES (?,?,?)";
		try (PreparedStatement ps = cnx.prepareStatement(sql)) {
			ps.setString(1, dto.getNombre());
			ps.setString(2, dto.getCorreo());
			ps.setString(3, dto.getTelefono());
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error al guardar: "+e.getMessage());
		}
	}

	@Override
	public ContactoDTO buscarPorId(int id) {
		try (PreparedStatement ps = cnx.prepareStatement("SELECT id, nombre, correo, telefono FROM contactos WHERE id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return mapear(rs);
				}
			}
		}catch(SQLException e) {
			System.out.println("Error al buscar: "+e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizar(ContactoDTO dto) {
		try (PreparedStatement ps = cnx.prepareStatement("UPDATE contactos SET nombre = ?, correo = ?, telefono = ? WHERE id = ?")) {
			ps.setString(1, dto.getNombre());
			ps.setString(2, dto.getCorreo());
			ps.setString(3, dto.getTelefono());
			ps.setInt(4, dto.getId());
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error al actualizar: "+e.getMessage());
		}
	}

	@Override
	public void eliminar(int id) {
		try (PreparedStatement ps = cnx.prepareStatement("DELETE FROM contactos WHERE id = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error al eliminar: "+e.getMessage());
		}	
	}

	private ContactoDTO mapear(ResultSet rs) throws SQLException {
		return new ContactoDTO(
				rs.getInt("id"),
				rs.getString("nombre"),
				rs.getString("correo"),
				rs.getString("telefono")
				);
	}
}
