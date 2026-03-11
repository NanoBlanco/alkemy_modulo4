package com.curso.blog.controller;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para Usuarios.
 *
 * Capa: Controller → Service → Repository → BD
 *
 * El Controller SOLO se encarga de:
 *  1. Recibir la petición HTTP
 *  2. Delegar al Service
 *  3. Retornar la respuesta HTTP
 * NUNCA contiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // POST /api/v1/usuarios
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponse>> crearUsuario(
            @Valid @RequestBody CrearUsuarioRequest request) {
        UsuarioResponse usuario = usuarioService.crearUsuario(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Usuario creado exitosamente", usuario));
    }

    // GET /api/v1/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(
            ApiResponse.ok("OK", usuarioService.obtenerConPerfil(id)));
    }

    // GET /api/v1/usuarios
    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> listarActivos() {
        return ResponseEntity.ok(
            ApiResponse.ok("OK", usuarioService.listarActivos()));
    }

    // PUT /api/v1/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarUsuarioRequest request) {
        return ResponseEntity.ok(
            ApiResponse.ok("Usuario actualizado", usuarioService.actualizarUsuario(id, request)));
    }

    // PATCH /api/v1/usuarios/{id}/perfil
    @PatchMapping("/{id}/perfil")
    public ResponseEntity<ApiResponse<PerfilResponse>> actualizarPerfil(
            @PathVariable Long id,
            @Valid @RequestBody CrearPerfilRequest request) {
        return ResponseEntity.ok(
            ApiResponse.ok("Perfil actualizado", usuarioService.actualizarPerfil(id, request)));
    }

    // DELETE /api/v1/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> desactivarUsuario(@PathVariable Long id) {
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.ok(ApiResponse.ok("Usuario desactivado", null));
    }
}
