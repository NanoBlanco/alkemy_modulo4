
package com.inventario.controller;

import com.inventario.service.CategoriaService;
import com.inventario.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;
    public CategoriaController(CategoriaService service) { this.service = service; }

    @GetMapping
    public String listar(Model model) throws Exception {
        model.addAttribute("categorias", service.listar());
        return "categorias";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formCategoria";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria c) throws Exception {
        if (c.getId() == 0) service.guardar(c);
        else service.actualizar(c);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) throws Exception {
        model.addAttribute("categoria", service.obtener(id));
        return "formCategoria";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) throws Exception {
        service.eliminar(id);
        return "redirect:/categorias";
    }
}
