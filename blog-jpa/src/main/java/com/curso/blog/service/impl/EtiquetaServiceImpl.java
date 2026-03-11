package com.curso.blog.service.impl;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;
import com.curso.blog.entity.Etiqueta;
import com.curso.blog.exception.BlogExceptions.*;
import com.curso.blog.mapper.BlogMapper;
import com.curso.blog.repository.EtiquetaRepository;
import com.curso.blog.service.EtiquetaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EtiquetaServiceImpl implements EtiquetaService {

    private final EtiquetaRepository etiquetaRepo;
    private final BlogMapper         mapper;

    @Override
    @Transactional
    public EtiquetaResponse crearEtiqueta(CrearEtiquetaRequest request) {
        String nombre = request.getNombre().toLowerCase().trim();

        if (etiquetaRepo.existsByNombreIgnoreCase(nombre)) {
            throw new RecursoDuplicadoException("Ya existe la etiqueta: " + nombre);
        }

        Etiqueta etiqueta = mapper.toEtiqueta(request);
        return mapper.toEtiquetaResponse(etiquetaRepo.save(etiqueta));
    }

    @Override
    @Transactional(readOnly = true)
    public EtiquetaResponse obtenerPorId(Long id) {
        return etiquetaRepo.findById(id)
            .map(mapper::toEtiquetaResponse)
            .orElseThrow(() -> new RecursoNoEncontradoException("Etiqueta", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EtiquetaResponse> listarTodas() {
        return etiquetaRepo.findAll()
            .stream()
            .map(mapper::toEtiquetaResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EtiquetaResponse> listarMasUsadas() {
        return etiquetaRepo.findMasUsadas()
            .stream()
            .map(mapper::toEtiquetaResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EtiquetaResponse actualizarEtiqueta(Long id, CrearEtiquetaRequest request) {
        Etiqueta etiqueta = etiquetaRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Etiqueta", id));

        if (request.getNombre() != null) etiqueta.setNombre(request.getNombre().toLowerCase().trim());
        if (request.getColor()  != null) etiqueta.setColor(request.getColor());

        return mapper.toEtiquetaResponse(etiqueta);
    }

    @Override
    @Transactional
    public void eliminarEtiqueta(Long id) {
        Etiqueta etiqueta = etiquetaRepo.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Etiqueta", id));

        if (!etiqueta.getPosts().isEmpty()) {
            throw new OperacionInvalidaException(
                "No se puede eliminar la etiqueta '" + etiqueta.getNombre() +
                "' porque está en uso por " + etiqueta.getPosts().size() + " post(s)");
        }

        etiquetaRepo.delete(etiqueta);
    }
}
