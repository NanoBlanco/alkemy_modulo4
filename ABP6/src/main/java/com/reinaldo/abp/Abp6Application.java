package com.reinaldo.abp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.reinaldo.abp.entity.Usuario;
import com.reinaldo.abp.repository.UsuarioRepository;

@SpringBootApplication
public class Abp6Application {

	public static void main(String[] args) {
		SpringApplication.run(Abp6Application.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository repository, PasswordEncoder encoder) {
		return args -> {
			repository.save(
					Usuario.builder()
					.username("admin")
					.email("admin@mail.com")
					.password(encoder.encode("1234"))
					.role("ADMIN")
					.build()
					);
		};
	}
}
