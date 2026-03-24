package com.reinaldo.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.reinaldo.api.modelo.AuthResponseDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	@Autowired
	RestTemplate rest;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String auth(
			@RequestParam String username, 
			@RequestParam String password, 
			HttpSession session, 
			Model model) {
		String url = "http://localhost:9090/api/auth/login";
		Map<String, String> body = new HashMap<>();
		body.put("username", username);
		body.put("password", password);
		try {
			ResponseEntity<AuthResponseDTO> response = rest.postForEntity(url, body, AuthResponseDTO.class);
			
			AuthResponseDTO auth = response.getBody();
			
			session.setAttribute("token", auth.token());
			session.setAttribute("username", auth.username());
			session.setAttribute("role", auth.role());
			
			return "redirect:/home";
		} catch(Exception e) {
			model.addAttribute("error", "Credenciales inválidas");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
