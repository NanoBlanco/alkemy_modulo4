package com.reinaldo.demo.service;

import org.springframework.stereotype.Service;

import com.reinaldo.demo.modelo.Usuario;

@Service
public class UsuarioService {

	public void guardar(Usuario u) {
		System.out.println("Guardando usuario: "+u.getNombre());
	}
}
