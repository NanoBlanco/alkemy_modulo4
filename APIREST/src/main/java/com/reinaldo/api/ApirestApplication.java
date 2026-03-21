package com.reinaldo.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.reinaldo.api.entity.Usuario;
import com.reinaldo.api.repository.UsuarioRepository;

@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UsuarioRepository repository, PasswordEncoder encoder) {
		return args -> {
			repository.save(
					Usuario.builder()
					.username("admin")
					.password(encoder.encode("1234"))
					.role("ADMIN")
					.build()
					);
		};
	}

}
