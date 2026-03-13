package com.reinaldo.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TareaRequestDTO {

	@NotBlank(message = "El titulo es obligatorio")
	private String titulo;
	
	private String descripcion;
	
	@NotNull(message = "Debe indicar si esta completada")
	private Boolean completada;

	public String getTitulo() { return titulo; }

	public void setTitulo(String titulo) { this.titulo = titulo; }

	public String getDescripcion() { return descripcion; }

	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

	public Boolean getCompletada() { return completada; }

	public void setCompletada(Boolean completa) { this.completada = completa; }
	
}
