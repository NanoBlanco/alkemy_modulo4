package com.reinaldo.gestor.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false, unique = true)
	private String correo;
	private String clave;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuarios_roles", joinColumns=@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="rol_id"))
	private Set<Rol> roles = new HashSet<>();
	
	// Metodos de ayuda (recomendado)
	
	public void addRol(Rol rol) {
		this.roles.add(rol);
		rol.getUsuarios().add(this);
	}
	
	public void removeRol(Rol rol) {
		this.roles.remove(rol);
		rol.getUsuarios().remove(this);
	}

}
