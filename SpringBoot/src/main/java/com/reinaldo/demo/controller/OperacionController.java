package com.reinaldo.demo.controller;

import org.springframework.aop.support.AopUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.demo.service.OperacionService;

@RestController
public class OperacionController {

	private final OperacionService service;
	
	public OperacionController(OperacionService service) {
		this.service = service;
	}
	
	@GetMapping("/operar")
	public String operar() {
		return service.ejecutarOperaciones();
	}
	
	@GetMapping("/tipo")
	public String tipo() {
		return service.getClass().toString();
	}
	
	@GetMapping("/esProxy")
	public String esProxy() {
		return String.valueOf(AopUtils.isAopProxy(service));
	}
}
