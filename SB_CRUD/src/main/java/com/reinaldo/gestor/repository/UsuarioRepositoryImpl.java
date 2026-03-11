package com.reinaldo.gestor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reinaldo.gestor.mapper.UsuarioMapper;
import com.reinaldo.gestor.model.Usuario;

@Profile("jdbc")
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	private final JdbcTemplate jdbc;

	public UsuarioRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public List<Usuario> findAll() {
		String sql = "SELECT * FROM usuarios";
		
		return jdbc.query(sql, new UsuarioMapper());
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
		try {
			Usuario u = jdbc.queryForObject(sql, new UsuarioMapper(), id);
			return Optional.of(u);
		}catch (EmptyResultDataAccessException e) {			
			return Optional.empty();
		}
	}

	@Override
	public void save(Usuario u) {
		String sql = "INSERT INTO usuarios (nombre, correo, clave) VALUES (?,?,?)";
		jdbc.update(sql, u.getNombre(), u.getCorreo(), u.getClave());
	}

	@Override
	public void update(Usuario u) {
		String sql = "UPDATE usuarios SET nombre=?, correo=?, clave=? WHERE id_usuario = ?";
		
		jdbc.update(sql, u.getNombre(), u.getCorreo(), u.getClave(), u.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
		jdbc.update(sql, id);
	}

	@Override
	public List<Usuario> findAllWithRoles() {
		return null;
	}

}
