package com.reinaldo.api.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reinaldo.api.dto.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> manejarNotFound(ResourceNotFoundException ex) {
		
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(),
				ex.getMessage()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> manejarValidacion(MethodArgumentNotValidException ex) {
		
		String errores = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(e -> e.getField() + ": "+ e.getDefaultMessage())
				.collect(Collectors.joining(", "));
		
		ApiError error = new ApiError(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(),
				errores
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
