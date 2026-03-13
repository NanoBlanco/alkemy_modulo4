package com.reinaldo.api.service;

import java.util.List;

import com.reinaldo.api.dto.TareaRequestDTO;
import com.reinaldo.api.dto.TareaResponseDTO;

public interface TareaService {
	
	TareaResponseDTO crear(TareaRequestDTO dto);
	
	List<TareaResponseDTO> listar();

	TareaResponseDTO buscarporId(Long id);
	
	TareaResponseDTO actualizar(Long id, TareaRequestDTO dto);
	
	void eliminar(Long id);
}
