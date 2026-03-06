package com.reinaldo.demo.modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Usuario {
	
	@NotBlank(message="El nombre es obligatorio")
	@Size(min=3, message="El nombre debe tener al menos 3 caracteres")
	private String nombre;
	
	@NotBlank(message="El correo es obligatorio")
	@Email(message="Debe ingresar un correo válido")
	private String correo;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}
