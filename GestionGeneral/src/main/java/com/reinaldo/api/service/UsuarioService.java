package com.reinaldo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.reinaldo.api.modelo.Usuario;

import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioService {

	@Autowired
	RestTemplate rest;
	
	private final String API = "http://localhost:9090";
	private String ruta;
	
	public List<Usuario> listar(HttpSession session){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + session.getAttribute("token"));
		
		Usuario u = null;
		ruta = API.concat("/api/usuarios");
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(u, headers);
		ResponseEntity<List<Usuario>> response = rest.exchange(
				ruta, 
				HttpMethod.GET, 
				request, 
				new ParameterizedTypeReference<List<Usuario>>() {});
		return response.getBody();
	}
	
	public Usuario obtenerPorId(Long id) {
		Usuario u = null;
		ruta = API.concat("/api/usuarios/")+id;
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(u);
		ResponseEntity<Usuario> response = rest.exchange(ruta, HttpMethod.GET, request, new ParameterizedTypeReference<Usuario>() {});
		return response.getBody();
	}
	
	public void crear(Usuario u, HttpSession session) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + session.getAttribute("token"));
		ruta = API.concat("/api/usuarios");
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(u, headers);
		rest.postForEntity(ruta, request, String.class);
	}
	
	public void actualizar(Usuario u) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ruta = API.concat("/api/usuarios/")+u.getId();
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(u, headers);
		rest.put(ruta, request, String.class);
	}
	
	public void eliminar(Long id) {
		ruta = API.concat("/api/usuarios/");
		rest.delete(ruta + id);
	}
}
