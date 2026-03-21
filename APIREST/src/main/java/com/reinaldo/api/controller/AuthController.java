package com.reinaldo.api.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.AuthRequest;
import com.reinaldo.api.service.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	
	@PostMapping("/login")
	public String login(@Valid @RequestBody AuthRequest request) {
		
		try {
			
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.username(), 
							request.password()
							)
					);
			
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			
			return jwtService.generarToken(userDetail);
		}catch(Exception e) {
			System.out.println("Error auth: "+e.getMessage());
			throw e;
		}
	}
}
