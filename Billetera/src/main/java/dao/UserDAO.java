package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.DBConnection;
import interfaces.IUserDAO;
import modelos.Usuario;

public class UserDAO implements IUserDAO {
	
	// De esta manera permite concurrencia web, multiples requests, es thread-safe.
	private final List<Usuario> usuarios = Collections.synchronizedList(new ArrayList<>());
	
	public UserDAO() {
		usuarios.add(new Usuario(
				1,"1234A",
				"Administrador",
				"admin@mail.com",
				"ADMIN"
				));		
	}
	
	public Usuario buscarUsuario(String username) {
		return usuarios.stream()
				.filter(u -> u.getCorreo().equals(username))
				.findFirst()
				.orElse(null);
	}
	
	public Usuario buscarPorId(Integer id) {
		return usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst().orElse(null);
	}
	
	public List<Usuario> listar(){
		List<Usuario> users = new ArrayList<>();
		String consulta = "SELECT u.id_usuario, u.nombre, u.clave, u.correo FROM usuarios u";
		Connection conn = null;
		PreparedStatement stmt = null;
		Usuario user = null;
		try {
			conn = DBConnection.getConnection();
			if(conn == null) {
        		throw new SQLException("Problemas con conexión a la base de datos Usuarios");
        	}
			stmt = conn.prepareStatement(consulta);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user = new Usuario(rs.getInt("id_usuario"),rs.getString("nombre"),rs.getString("correo"),rs.getString("clave"),"USER");
				users.add(user);
			}
			System.out.println("Lista Usuarios");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void guardar(Usuario u) {
		int id = usuarios.stream()
				.mapToInt(Usuario::getId)
				.max().orElse(0)+1;
		
		u.setId(id);
		usuarios.add(u);
	}
	
	@Override
	public void eliminar(Integer id) {
		int idx = usuarios.indexOf(id);
		usuarios.remove(idx);
	}

	@Override
	public void actualizar(Usuario u) {
		Usuario actual = buscarPorId(u.getId());
		if(actual != null) {
			actual.setNombre(u.getNombre());
			actual.setCorreo(u.getCorreo());
			actual.setCorreo(u.getRol());
		}
	}
}
