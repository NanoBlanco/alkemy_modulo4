package com.reinaldo.api.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.reinaldo.api.service.CustomUserDetailsService;
import com.reinaldo.api.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final CustomUserDetailsService userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
		    filterChain.doFilter(request, response);
		    return;
		}
		
		if(request.getServletPath().contains("/api/auth")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		try {
			String token = authHeader.substring(7);
			
			String username = jwtService.extractUsername(token);
			List<String> roles = jwtService.extraerRoles(token);
			
			List<SimpleGrantedAuthority> authorities = roles.stream()
					.map(SimpleGrantedAuthority::new)
					.toList();
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails userDetails = userDetailService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken auth = 
						new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities()); 
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception ex) {
			System.out.println("Error JWT: "+ex.getMessage());
		}
		
		filterChain.doFilter(request, response);
	}

}
