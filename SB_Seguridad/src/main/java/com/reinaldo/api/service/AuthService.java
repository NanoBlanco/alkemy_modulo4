package com.reinaldo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.reinaldo.api.dto.AuthLoginRequest;
import com.reinaldo.api.dto.AuthLoginResponse;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private JwtService jwtService;

    public AuthLoginResponse login(AuthLoginRequest request) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        
        UserDetails user = (UserDetails) authentication.getPrincipal();

        //SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthLoginResponse(
                request.username(),
                token,
                refreshToken,
                true
        );
    }
}