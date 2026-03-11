package com.curso.blog.controller;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.service.EtiquetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/etiquetas")
@RequiredArgsConstructor
public class EtiquetaController {

    private final EtiquetaService etiquetaService;

    @PostMapping
    public ResponseEntity<ApiResponse<EtiquetaResponse>> crearEtiqueta(
            @Valid @RequestBody CrearEtiquetaRequest request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Etiqueta creada", etiquetaService.crearEtiqueta(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EtiquetaResponse>> obtenerEtiqueta(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("OK", etiquetaService.obtenerPorId(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EtiquetaResponse>>> listarEtiquetas() {
        return ResponseEntity.ok(ApiResponse.ok("OK", etiquetaService.listarTodas()));
    }

    @GetMapping("/mas-usadas")
    public ResponseEntity<ApiResponse<List<EtiquetaResponse>>> masUsadas() {
        return ResponseEntity.ok(ApiResponse.ok("OK", etiquetaService.listarMasUsadas()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EtiquetaResponse>> actualizarEtiqueta(
            @PathVariable Long id,
            @Valid @RequestBody CrearEtiquetaRequest request) {
        return ResponseEntity.ok(
            ApiResponse.ok("Etiqueta actualizada", etiquetaService.actualizarEtiqueta(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarEtiqueta(@PathVariable Long id) {
        etiquetaService.eliminarEtiqueta(id);
        return ResponseEntity.ok(ApiResponse.ok("Etiqueta eliminada", null));
    }
}
