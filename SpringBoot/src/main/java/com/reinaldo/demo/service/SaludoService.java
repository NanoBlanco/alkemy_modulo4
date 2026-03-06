package com.reinaldo.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {

	public SaludoService() {
		System.out.println("Creando SaludoService");
	}
	
	public String saludarService() {
		return "Hola, como están?";
	}
}
