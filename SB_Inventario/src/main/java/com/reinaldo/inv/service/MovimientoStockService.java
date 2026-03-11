package com.reinaldo.inv.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.inv.model.MovimientoStock;
import com.reinaldo.inv.model.Producto;
import com.reinaldo.inv.model.TipoMovimiento;
import com.reinaldo.inv.repository.MovimientoStockRepository;
import com.reinaldo.inv.repository.ProductoRepository;

@Service
public class MovimientoStockService {
	
	@Autowired
	private ProductoRepository repoProd;

	@Autowired
	private MovimientoStockRepository repoMov;
	
	public void registrarMovimiento(int productId, int cantidad, TipoMovimiento tipo, String observacion) {
		Producto prod = repoProd.findById(productId)
				.orElseThrow(()-> new RuntimeException("Producto no encontrado"));
		
		MovimientoStock mov = new MovimientoStock();
		mov.setProducto(prod);
		mov.setCantidad(cantidad);
		mov.setTipo(tipo);
		mov.setObservacion(observacion);
		mov.setFecha(LocalDateTime.now());
		
		repoMov.save(mov);
		
		if(tipo == TipoMovimiento.ENTRADA) {
			prod.setStock(cantidad+prod.getStock());
		} else {
			prod.setStock(prod.getStock()-cantidad);
		}
		
		repoProd.save(prod);
	}
}
