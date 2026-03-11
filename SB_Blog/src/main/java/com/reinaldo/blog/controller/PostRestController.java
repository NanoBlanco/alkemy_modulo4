package com.reinaldo.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.blog.dto.CreatePostDTO;
import com.reinaldo.blog.dto.PostDTO;
import com.reinaldo.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

	@Autowired
	private PostService service;
	
	@GetMapping
	public List<PostDTO> list(){
		return service.getAllPosts();
	}
	
	@GetMapping("/tag/{name}")
	public List<PostDTO> byTag(@PathVariable String name){
		return service.getPostsByTag(name);
	}
	
	@PostMapping
	public PostDTO create(@RequestBody CreatePostDTO post) {
		return service.create(post);
	}
	
}
