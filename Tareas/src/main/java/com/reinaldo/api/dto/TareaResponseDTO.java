package com.reinaldo.api.dto;

public class TareaResponseDTO {

	private Long id;
	private String titulo;
	private String descripcion;
	private Boolean completada;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public String getTitulo() { return titulo; }
	
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	public String getDescripcion() { return descripcion; }
	
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	
	public Boolean getCompletada() { return completada; }
	
	public void setCompletada(Boolean completada) { this.completada = completada; }
	
}
