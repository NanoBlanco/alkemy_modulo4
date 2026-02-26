package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;

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
				ContactoDTO c = new ContactoDTO(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("correo"),
						rs.getString("telefono")
						);
			lista.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}

	@Override
	public ContactoDTO buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(ContactoDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

}
