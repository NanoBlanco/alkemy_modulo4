package com.reinaldo.gestor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.reinaldo.gestor.model.Usuario;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM usuarios")
	List<Usuario> findAll();
	
	@Select("SELECT * FROM usuarios WHERE id_usuario=#{id}")
	Usuario findById(Long id);
	
	@Insert("INSERT INTO usuarios (nombre, correo, clave) VALUES (#{nombre}, #{correo}, #{clave})")
	void save(Usuario u);
	
	@Update("UPDATE usuarios SET nombre=#{nombre}, correo=#{correo}, clave=#{clave}")
	void update(Usuario u);
	
	@Delete("DELETE FROM usuarios WHERE id_usuario=#{id}")
	void delete(Long id);
}
