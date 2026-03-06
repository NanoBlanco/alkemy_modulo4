package com.reinaldo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.demo.service.SaludoService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Scope("prototype")
@RestController
public class HolaController {

	@Autowired
	SaludoService ser;
	
	/*
	public HolaController(SaludoService ser) {
		System.out.println("Constructor");
		this.ser = ser;
	}
	*/
	@PostConstruct
	public void init() {
		System.out.println("PostConstruct ejecutado");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("PreDestroy ejecutado");
	}
	
	@GetMapping("/saludo")
	public String saludar(@RequestParam String nombre) {
		return ser.saludarService();
	}
	
}
