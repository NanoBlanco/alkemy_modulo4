package com.reinaldo.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.reinaldo.api.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
		.authorizeHttpRequests(http -> {
			http.requestMatchers("/api/login").permitAll();
			http.requestMatchers(HttpMethod.GET, "/api/publico").hasRole("READ");
			http.requestMatchers(HttpMethod.GET,"/api/privado").hasAuthority("CREATE");
			http.anyRequest().authenticated();
		});
		
		return httpSecurity.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailService);
		return provider;
	}
	/*
	@Bean
	UserDetailsService userDetailsService() {
		List<UserDetails> userDetailList = new ArrayList<>();
		
		userDetailList.add(User.withUsername("admin")
				.password("1234")
				.roles("ADMIN")
				.authorities("READ", "CREATE")
				.build()
				);
		
		userDetailList.add(User.withUsername("usuario")
				.password("1234")
				.roles("USER")
				.authorities("READ")
				.build()
				);
		
		return new InMemoryUserDetailsManager(userDetailList);
	}
	*/
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
