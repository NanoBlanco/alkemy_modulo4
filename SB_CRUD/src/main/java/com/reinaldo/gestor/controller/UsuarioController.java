package com.reinaldo.gestor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reinaldo.gestor.model.Usuario;
import com.reinaldo.gestor.repository.RolRepository;
import com.reinaldo.gestor.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService service;
	private final RolRepository repoRol;
	
	public UsuarioController(UsuarioService service, RolRepository repoRol) {
		this.service = service;
		this.repoRol = repoRol;
	}
	
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("usuarios", service.listarUsuarios());
		return "usuarios";
	}
	
	@GetMapping("/nuevo")
	public String form(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("roles", repoRol.findAll());
		return "usuario-form";
	}
	
	@PostMapping
	public String grabar(Usuario u) {
		service.createUsuario(u);
		
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("usuario", service.obtenerUsuario(id));
		return "usuario-form";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteUsuario(id);
		return "redirect:/usuarios";
	}
}
