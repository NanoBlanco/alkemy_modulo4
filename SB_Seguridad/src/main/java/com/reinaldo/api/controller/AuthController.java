package com.reinaldo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.AuthLoginRequest;
import com.reinaldo.api.dto.AuthLoginResponse;
import com.reinaldo.api.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest userRequest){
		return ResponseEntity.ok(authService.login(userRequest));
	}

}
