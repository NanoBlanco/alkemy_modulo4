package com.reinaldo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reinaldo.api.modelo.Usuario;
import com.reinaldo.api.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService service;
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("usuarios", service.listar(session));
		return "usuarios";
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/registro")
	public String grabar(@ModelAttribute Usuario u, HttpServletRequest request) {
		HttpSession session = request.getSession();
		service.crear(u, session);
		return "redirect:/usuarios";
	}
	
	
}
