package com.reinaldo.inv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reinaldo.inv.model.Producto;
import com.reinaldo.inv.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	ProductoService service;
	
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("productos", service.listar());
		return "productos";
	}
	
	@GetMapping("/nuevo")
	public String formulario(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("categorias", service.listarCategorias());
		return "producto-form";
	}
	
	@PostMapping("/guardar")
	public String guardar(Producto p) {
		service.guardar(p);
		return "redirect:/productos";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("producto", service.obtener(id))	;
		model.addAttribute("categorias", service.listarCategorias());
		return "producto-form";

	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, Model model) {
		service.eliminar(id);
		return "redirect:/productos";
	}
}