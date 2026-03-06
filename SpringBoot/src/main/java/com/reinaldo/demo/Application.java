package com.reinaldo.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = 
				SpringApplication.run(Application.class, args);
		
		System.out.println("Cantidad de Beans: "+c.getBeanDefinitionCount());
		
		//Arrays.stream(c.getBeanDefinitionNames()).sorted().forEach(System.out::println);
	}

}
