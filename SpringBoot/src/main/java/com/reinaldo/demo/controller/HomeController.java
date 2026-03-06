package com.reinaldo.demo.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String inicio(Model model) {
		model.addAttribute("mensaje","Bienvenido a Spring Boot con Thymeleaf");
		model.addAttribute("fecha", LocalDateTime.now());
		return "index";
	}
}
