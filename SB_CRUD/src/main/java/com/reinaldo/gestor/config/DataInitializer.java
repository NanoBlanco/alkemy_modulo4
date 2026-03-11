package com.reinaldo.gestor.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reinaldo.gestor.model.Rol;
import com.reinaldo.gestor.repository.RolRepository;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initRoles(RolRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				repo.save(new Rol(null, "ADMIN"));
				repo.save(new Rol(null, "USER"));
				repo.save(new Rol(null, "MANAGER"));
			}
		};
		
	}
}
