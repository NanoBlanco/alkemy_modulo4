package com.reinaldo.api;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.reinaldo.api.entity.Permiso;
import com.reinaldo.api.entity.Role;
import com.reinaldo.api.entity.RoleEnum;
import com.reinaldo.api.entity.Usuario;
import com.reinaldo.api.repository.UsuarioRepository;

@SpringBootApplication
public class ProyectoSsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSsApplication.class, args);
	}

	@Bean
	CommandLineRunner iniciar(UsuarioRepository repo, BCryptPasswordEncoder passEncoder) {
		return args -> {
			String clave = passEncoder.encode("123456");
			
			// Authorities
			Permiso crear = Permiso.builder().nombre("CREATE").build();
			Permiso leer = Permiso.builder().nombre("READ").build();
			Permiso actualizar = Permiso.builder().nombre("UPDATE").build();
			Permiso eliminar = Permiso.builder().nombre("DELETE").build();

			//Role
			Role admin = Role.builder()
					.role(RoleEnum.ADMIN)
					.permisos(Set.of(crear,leer,actualizar,eliminar))
					.build();
			Role usuario = Role.builder()
					.role(RoleEnum.USER)
					.permisos(Set.of(leer))
					.build();
			Role supervisor = Role.builder()
					.role(RoleEnum.SUPERVISOR)
					.permisos(Set.of(crear,leer,actualizar))
					.build();
			
			//Usuarios
			Usuario renny = Usuario.builder()
					.nombre("Reinaldo Blanco")
					.correo("rey@mail.com")
					.password(clave).roles(Set.of(admin))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.build();
			
			Usuario claudia = Usuario.builder()
					.nombre("Claudia Villegas")
					.correo("claudia@mail.com")
					.password(clave).roles(Set.of(usuario))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.build();
			
			repo.saveAll(List.of(renny, claudia));
		};
	}
}
