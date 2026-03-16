package com.reinaldo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.reinaldo.api.dto.AuthLoginRequest;
import com.reinaldo.api.dto.AuthLoginResponse;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authManager;

    public AuthLoginResponse login(AuthLoginRequest request) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new AuthLoginResponse(
                request.username(),
                "User logged successfully",
                true
        );
    }
}