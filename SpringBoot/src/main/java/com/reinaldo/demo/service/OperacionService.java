package com.reinaldo.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OperacionService{

	public String ejecutarOperaciones() {
		System.out.println("Ejecutando logica real...");
		return "Operacion Completada";
	}
	
	public void metodoExterno() {
		metodoInterno();
	}
	
	@Transactional
	public void metodoInterno() {
		System.out.println("Transacción activa");
	}
}
