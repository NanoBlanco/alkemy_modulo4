package com.reinaldo.abp.mapper;

import org.springframework.stereotype.Component;

import com.reinaldo.abp.dto.usuario.UsuarioResponseDTO;
import com.reinaldo.abp.entity.Usuario;


@Component
public class UsuarioMapper {
	
	public UsuarioResponseDTO mapToResponse(Usuario u) {
		return UsuarioResponseDTO.builder()
				.id(u.getId())
				.username(u.getUsername())
				.role(u.getRole())
				.email(u.getEmail())
				.build();
	}

}
