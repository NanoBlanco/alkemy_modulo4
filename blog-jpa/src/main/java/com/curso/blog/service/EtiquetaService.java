package com.curso.blog.service;

import com.curso.blog.dto.request.RequestDTOs.*;
import com.curso.blog.dto.response.ResponseDTOs.*;

import java.util.List;

public interface EtiquetaService {
    EtiquetaResponse        crearEtiqueta(CrearEtiquetaRequest request);
    EtiquetaResponse        obtenerPorId(Long id);
    List<EtiquetaResponse>  listarTodas();
    List<EtiquetaResponse>  listarMasUsadas();
    EtiquetaResponse        actualizarEtiqueta(Long id, CrearEtiquetaRequest request);
    void                    eliminarEtiqueta(Long id);
}
