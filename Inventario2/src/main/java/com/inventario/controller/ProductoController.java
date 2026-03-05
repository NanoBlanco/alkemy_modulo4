
package com.inventario.controller;

import com.inventario.service.*;
import com.inventario.model.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;
    private final CategoriaService catService;

    public ProductoController(ProductoService service, CategoriaService catService) {
        this.service = service;
        this.catService = catService;
    }

    @GetMapping
    public String listar(Model model) throws Exception {
        model.addAttribute("productos", service.listar());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) throws Exception {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", catService.listar());
        return "formProducto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto p) throws Exception {
        if (p.getId() == 0) service.guardar(p);
        else service.actualizar(p);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) throws Exception {
        model.addAttribute("producto", service.obtener(id));
        model.addAttribute("categorias", catService.listar());
        return "formProducto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) throws Exception {
        service.eliminar(id);
        return "redirect:/productos";
    }
}
