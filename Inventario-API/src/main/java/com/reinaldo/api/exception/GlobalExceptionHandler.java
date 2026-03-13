package com.reinaldo.api.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(
			ResourceNotFoundException ex,
			HttpServletRequest req){
		ApiError error = ApiError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error("Not Found")
				.message(ex.getMessage())
				.path(req.getRequestURI())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> manejarValidacion(MethodArgumentNotValidException ex, HttpServletRequest req) {
		
		String errores = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(e -> e.getField() + ": "+ e.getDefaultMessage())
				.collect(Collectors.joining(", "));
		
		ApiError error = ApiError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.name())
				.message(errores)
				.path(req.getRequestURI())
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
