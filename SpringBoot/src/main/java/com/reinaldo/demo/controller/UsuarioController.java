package com.reinaldo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reinaldo.demo.modelo.Usuario;
import com.reinaldo.demo.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
	private final UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "formulario";
	}
	
	@PostMapping("/formulario")
	public String procesarFormulario(
			@Valid @ModelAttribute Usuario usuario, 
			BindingResult result,
			RedirectAttributes redirectAtt) {
		
		if (result.hasErrors()) {
			return "formulario";
		}
		
		service.guardar(usuario);
		
		//model.addAttribute("usuario", usuario);
		//return "resultado";
		redirectAtt.addFlashAttribute("mensajeExito", "Usuario registrado correctamente");
		return "redirect:/formulario";
	}
}
