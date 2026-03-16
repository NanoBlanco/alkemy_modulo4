package com.reinaldo.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestController {

	@GetMapping("/publico")
	public String hola() {
		return "Hola Mundo";
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/privado")
	public String privado() {
		return "Hola Mundo Privado";
	}
	
	@PreAuthorize("hasAuthority('CREATE')")
	@GetMapping("/crear")
	public String crear() {
		return "Creando recurso";
	}
	
	@GetMapping("/otro")
	public String otro() {
		return "Otro proceso";
	}
}
