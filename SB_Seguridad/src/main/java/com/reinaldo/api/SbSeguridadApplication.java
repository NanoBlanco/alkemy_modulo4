package com.reinaldo.api;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.reinaldo.api.entity.Permiso;
import com.reinaldo.api.entity.Role;
import com.reinaldo.api.entity.RoleEnum;
import com.reinaldo.api.entity.Usuario;
import com.reinaldo.api.repository.UsuarioRepository;

@SpringBootApplication
public class SbSeguridadApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SbSeguridadApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
		System.out.println("Llega a generar usuarios");
		return args -> {
			String claveEncriptada = passwordEncoder.encode("123456");
			
			Permiso crear = Permiso.builder().nombre("CREATE").build();
			Permiso leer = Permiso.builder().nombre("READ").build();
			Permiso actualizar = Permiso.builder().nombre("UPDATE").build();
			Permiso eliminar = Permiso.builder().nombre("DELETE").build();
			
			Role admin = Role.builder()
					.role(RoleEnum.ADMIN)
					.permisos(Set.of(crear, leer, actualizar, eliminar))
							.build();
			Role usuario = Role.builder()
					.role(RoleEnum.USER)
					.permisos(Set.of(leer)).build();
			Role developer = Role.builder()
					.role(RoleEnum.MANAGER)
					.permisos(Set.of(crear,leer, actualizar))
					.build();
			
			Usuario renny = Usuario.builder()
					.nombre("Reynaldo Blanco")
					.correo("rey@mail.com")
					.password(claveEncriptada)
					.roles(Set.of(admin, developer))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.build();
			
			Usuario vale = Usuario.builder()
					.nombre("Valentina Villarroel")
					.correo("vale@mail.com")
					.password(claveEncriptada)
					.roles(Set.of(usuario))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.build();
			
			repo.saveAll(List.of(renny, vale));
		};
	}
}
