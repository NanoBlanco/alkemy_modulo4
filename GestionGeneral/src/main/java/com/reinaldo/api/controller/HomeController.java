package com.reinaldo.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String inicio(HttpSession session) {
		validarSesion(session);
		return "home";
	}
	
	
	private void validarSesion(HttpSession session) {
		if(session.getAttribute("token") == null) {
			throw new RuntimeException("No autenticado");
		}
	}
}
