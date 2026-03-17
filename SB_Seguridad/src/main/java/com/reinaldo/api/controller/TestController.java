package com.reinaldo.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class TestController {

	
	@GetMapping("/login")
	public String hola() {
		return "login";
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/privado")
	@ResponseBody
	public String privado() {
		return "Hola Mundo Privado";
	}
	
	@PreAuthorize("hasAuthority('CREATE')")
	@GetMapping("/crear")
	@ResponseBody
	public String crear() {
		return "Creando recurso";
	}
	
	
	@GetMapping("/dashboard")
	public String otro(Model model, Authentication auth) {
		model.addAttribute("username", auth.getName());
		model.addAttribute("roles", auth.getAuthorities());
		return "dashboard";
	}
}
