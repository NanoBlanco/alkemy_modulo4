package com.reinaldo.gestor.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reinaldo.gestor.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFound(
			UserNotFoundException ex,
			Model model) {
		
		model.addAttribute("error", ex.getMessage());
		return "error";
	}
}
