package com.reinaldo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.api.dto.AuthLoginRequest;
import com.reinaldo.api.dto.AuthLoginResponse;
import com.reinaldo.api.dto.RefreshTokenRequest;
import com.reinaldo.api.dto.RefreshTokenResponse;
import com.reinaldo.api.service.AuthService;
import com.reinaldo.api.service.JwtService;
import com.reinaldo.api.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserDetailsServiceImpl userDetail;
	
	@PostMapping("/login")
	public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest userRequest){
		return ResponseEntity.ok(authService.login(userRequest));
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
		String username = jwtService.extractUsername(request.refreshToken());
		
		UserDetails user = userDetail.loadUserByUsername(username);
		
		String newAccessToken = jwtService.generateToken(user);
		
		return ResponseEntity.ok(new RefreshTokenResponse(newAccessToken));
	}
}
