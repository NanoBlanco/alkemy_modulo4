package com.reinaldo.api.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	private Long id;
	private String username;
	private String email;
	private String password;
	private String role;
}
