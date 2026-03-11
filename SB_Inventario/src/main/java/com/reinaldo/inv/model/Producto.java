package com.reinaldo.inv.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    private String nombre;
    private String descripcion;
    private int precio;
    private int stock;
    private int stockMin;
    
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
    private boolean activo;
    private LocalDateTime fechaAlta;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<MovimientoStock> movimientos;
}
