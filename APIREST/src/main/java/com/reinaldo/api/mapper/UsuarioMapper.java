package com.reinaldo.api.mapper;

import org.springframework.stereotype.Component;

import com.reinaldo.api.dto.UsuarioResponseDTO;
import com.reinaldo.api.entity.Usuario;

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
