package com.reinaldo.api.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	//@Value("${security.key}")
	//private String privateKey;
	
	private static final String SECRET_KEY = "mi_super_clave_secreta_para_jwt_2026";
	
	public String generateToken(UserDetails user) {
		
		List<String> roles = user.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.toList();
		
		return Jwts.builder()
				.setSubject(user.getUsername())
				.claim("roles", roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 900000))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public List<String> extractRoles(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claims.get("roles", List.class);
	}
	
	public String generateRefreshToken(UserDetails user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.claim("roles", user.getAuthorities()
						.stream()
						.map(GrantedAuthority::getAuthority)
						.toList())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 9000000))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
}
