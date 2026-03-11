package com.curso.blog.service;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;

import java.util.List;

// ============================================================
//  Interfaces de Servicio — definen el CONTRATO de negocio
//  Las implementaciones están en service/impl/
// ============================================================

public interface UsuarioService {
    UsuarioResponse         crearUsuario(CrearUsuarioRequest request);
    UsuarioResponse         obtenerPorId(Long id);
    UsuarioResponse         obtenerConPerfil(Long id);
    List<UsuarioResponse>   listarActivos();
    UsuarioResponse         actualizarUsuario(Long id, ActualizarUsuarioRequest request);
    void                    desactivarUsuario(Long id);
    PerfilResponse          actualizarPerfil(Long usuarioId, CrearPerfilRequest request);
}
