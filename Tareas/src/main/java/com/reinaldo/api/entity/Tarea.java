package com.reinaldo.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tareas")
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	private String descripcion;
	
	@Column(nullable = false)
	private Boolean completada;
	
	public Tarea() { }

	public Tarea(Long id, String titulo, String descripcion, Boolean completada) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.completada = completada;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getTitulo() { return titulo; }

	public void setTitulo(String titulo) { this.titulo = titulo; }

	public String getDescripcion() { return descripcion; }

	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

	public Boolean getCompletada() { return completada; }

	public void setCompletada(Boolean completada) { this.completada = completada; }
	
}
