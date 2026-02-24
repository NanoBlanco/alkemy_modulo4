package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import modelo.Usuario;

public class UsuarioDAO {
	
	public List<Usuario> listar() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT id_usuario, nombre, clave, correo FROM Usuarios";
		
		try(Connection conn = DBConnection.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				Usuario u = new Usuario(
						rs.getInt("id_usuario"),
						rs.getString("nombre"),
						rs.getString("correo"),
						rs.getString("clave")
						);
				usuarios.add(u);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.getInstance().closeCnx();
		}
		
		return usuarios;
	}
	
	public Usuario getUsuario(String correo) {
		Usuario u = new Usuario();
		String sql = "SELECT id_usuario, nombre, clave, correo FROM usuarios WHERE correo = ?";
		try(Connection cnx = DBConnection.getInstance().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)) {
			ps.setString(1, correo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {				
				u.setId_usuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setClave(rs.getString("clave"));
				u.setCorreo(rs.getString("correo"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(u != null) return u;
		return null;
	}
	
	public boolean actualizar(Usuario u) {
		String sql = "UPDATE usuarios SET nombre = ?, clave = ? WHERE correo = ?";
		try(Connection cnx = DBConnection.getInstance().getConnection();
				PreparedStatement ps = cnx.prepareStatement(sql)) {
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getClave());
			ps.setString(3, u.getCorreo());
			int filas = ps.executeUpdate();
			
			return filas > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
