package com.reinaldo.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reinaldo.api.entity.Usuario;
import com.reinaldo.api.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario userEntity = repo.findByCorreo(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		userEntity.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));
		
		userEntity.getRoles()
		.stream()
		.flatMap(rol -> rol.getPermisos()
				.stream())
				.forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombre())));

		return new User(userEntity.getCorreo(),
				userEntity.getPassword(),
				userEntity.isEnabled(),
				userEntity.isAccountNoExpired(),
				userEntity.isAccountNoLocked(),
				userEntity.isCredentialNoExpired(),
				authorityList);
	}

}
