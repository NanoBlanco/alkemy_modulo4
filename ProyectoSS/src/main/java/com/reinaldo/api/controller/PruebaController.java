package com.reinaldo.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PruebaController {
	
	@GetMapping("/publico")
	public String publico() {
		return "Recurso publico";
	}
	
	@GetMapping("/privado")
	public String privado() {
		return "Este recurso es privado.";
	}

}
