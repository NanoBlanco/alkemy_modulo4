package com.reinaldo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {

	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/posts")
	public String posts() {
		return "posts";
	}
	
	@GetMapping("/posts/create")
	public String create() {
		return "create-post";
	}
}
