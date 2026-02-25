package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import interfaces.IUserDAO;
import modelos.Usuario;

public class UserDAO implements IUserDAO {
	
	private final Connection cnx;
	
	public UserDAO() {
		this.cnx = DBConnection.getConnection();
	}

	@Override
	public void guardar(Usuario entidad) {
		try (PreparedStatement ps = cnx.prepareStatement("INSERT INTO usuarios (nombre, correo, clave, rol) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getCorreo());
			ps.setString(3, entidad.getClave());
			ps.setString(4, entidad.getRol());
			ps.executeUpdate() ;
			System.out.println("Valores recibidos ");
			try (ResultSet keys = ps.getGeneratedKeys()){
				if(keys.next()) {
					entidad.setId(keys.getInt(1));
				}
			}
		}catch(SQLException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	
	@Override
	public Usuario buscarPorId(Integer id) {
		try (PreparedStatement ps = cnx.prepareStatement("SELECT id, nombre, correo, clave, rol FROM usuarios WHERE id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return mapear(rs);
				}
			}
		}catch(SQLException e) {
			System.out.println("Error: "+e.getMessage());
		}
		return null;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<>();
		try (PreparedStatement ps = cnx.prepareStatement("SELECT id, nombre, correo, clave, rol FROM usuarios");
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				lista.add(mapear(rs));
			}
		}catch(SQLException e) {
			System.out.println("Error: "+e.getMessage());
		}
		return lista;
	}

	@Override
	public void actualizar(Usuario entidad) {
		try (PreparedStatement ps = cnx.prepareStatement("UPDATE usuarios SET nombre = ?, correo = ?, rol = ? WHERE id = ?")) {
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getCorreo());
			ps.setString(3, entidad.getRol());
			ps.setInt(4, entidad.getId());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
	}

	@Override
	public void eliminar(Integer id) {
		try (PreparedStatement ps = cnx.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: "+e.getMessage());
		}		
	}

	@Override
	public Usuario buscarUsuario(String correo) {
		try (PreparedStatement ps = cnx.prepareStatement("SELECT id, nombre, correo, clave, rol FROM usuarios WHERE correo = ?")) {
			ps.setString(1, correo);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return mapear(rs);
				}
			}
		}catch(SQLException e) {
			System.out.println("Error: "+e.getMessage());
		}
		return null;
	}
	
	private Usuario mapear(ResultSet rs) throws SQLException {
		return new Usuario(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("correo"),
					rs.getString("clave"),
					rs.getString("rol")
				);
	}
	
}
