package com.curso.blog.service.impl;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.entity.Perfil;
import com.curso.blog.entity.Usuario;
import com.curso.blog.exception.BlogExceptions.*;
import com.curso.blog.mapper.BlogMapper;
import com.curso.blog.repository.UsuarioRepository;
import com.curso.blog.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Usuario.
 *
 * @Transactional garantiza que cada operación sea atómica:
 *   - Si algo falla → rollback automático
 *   - readOnly=true → optimización para consultas (sin dirty-checking)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final BlogMapper        mapper;

    // ================================================================
    // CREAR
    // ================================================================
    @Override
    @Transactional
    public UsuarioResponse crearUsuario(CrearUsuarioRequest request) {
        log.debug("Creando usuario con email: {}", request.getEmail());

        // 1. Validar unicidad del email
        if (usuarioRepo.existsByEmail(request.getEmail())) {
            throw new RecursoDuplicadoException(
                "Ya existe un usuario con el email: " + request.getEmail());
        }

        // 2. Mapear DTO → Entidad (el mapper también crea el Perfil si viene en el request)
        Usuario usuario = mapper.toUsuario(request);

        // 3. Si no viene perfil en el request, crear uno vacío por defecto
        if (usuario.getPerfil() == null) {
            Perfil perfilVacio = Perfil.builder().build();
            usuario.asignarPerfil(perfilVacio);  // Helper method — mantiene bidireccionalidad
        }

        // 4. Persistir (cascade guarda el Perfil también)
        Usuario guardado = usuarioRepo.save(usuario);
        log.info("Usuario creado con id: {}", guardado.getId());

        return mapper.toUsuarioResponse(guardado);
    }

    // ================================================================
    // LECTURA
    // ================================================================
    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = usuarioRepo.findByIdWithPerfil(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", id));
        return mapper.toUsuarioResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse obtenerConPerfil(Long id) {
        // JOIN FETCH carga perfil + posts en una sola consulta
        Usuario usuario = usuarioRepo.findByIdWithPerfilAndPosts(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", id));
        return mapper.toUsuarioResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarActivos() {
        return usuarioRepo.findAllActivosWithPosts()
            .stream()
            .map(mapper::toUsuarioResponse)
            .collect(Collectors.toList());
    }

    // ================================================================
    // ACTUALIZAR
    // ================================================================
    @Override
    @Transactional
    public UsuarioResponse actualizarUsuario(Long id, ActualizarUsuarioRequest request) {
        Usuario usuario = usuarioRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", id));

        // Actualizar solo campos no nulos
        if (request.getNombre() != null) usuario.setNombre(request.getNombre());
        if (request.getEmail()  != null) {
            if (!usuario.getEmail().equals(request.getEmail())
                    && usuarioRepo.existsByEmail(request.getEmail())) {
                throw new RecursoDuplicadoException(
                    "Ya existe un usuario con el email: " + request.getEmail());
            }
            usuario.setEmail(request.getEmail());
        }

        // JPA detecta los cambios (dirty checking) y hace UPDATE automáticamente
        return mapper.toUsuarioResponse(usuario);
    }

    @Override
    @Transactional
    public PerfilResponse actualizarPerfil(Long usuarioId, CrearPerfilRequest request) {
        Usuario usuario = usuarioRepo.findByIdWithPerfil(usuarioId)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", usuarioId));

        Perfil perfil = usuario.getPerfil();
        if (perfil == null) {
            perfil = Perfil.builder().build();
            usuario.asignarPerfil(perfil);
        }

        if (request.getBio()       != null) perfil.setBio(request.getBio());
        if (request.getFotoUrl()   != null) perfil.setFotoUrl(request.getFotoUrl());
        if (request.getSitioWeb()  != null) perfil.setSitioWeb(request.getSitioWeb());
        if (request.getUbicacion() != null) perfil.setUbicacion(request.getUbicacion());

        return mapper.toPerfilResponse(perfil);
    }

    // ================================================================
    // ELIMINAR (soft delete)
    // ================================================================
    @Override
    @Transactional
    public void desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", id));
        usuario.setActivo(false);
        log.info("Usuario {} desactivado", id);
        // JPA dirty-checking persiste el cambio al finalizar la transacción
    }
}
