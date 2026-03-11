package com.reinaldo.blog.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reinaldo.blog.domain.Post;
import com.reinaldo.blog.domain.Tag;
import com.reinaldo.blog.domain.User;
import com.reinaldo.blog.dto.CreatePostDTO;
import com.reinaldo.blog.dto.PostDTO;
import com.reinaldo.blog.mapper.PostMapper;
import com.reinaldo.blog.repository.PostRepository;
import com.reinaldo.blog.repository.TagRepository;
import com.reinaldo.blog.repository.UserRepository;

@Service
@Transactional
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	@Autowired
	private UserRepository repoUser;
	
	@Autowired
	private TagRepository repoTag;
	
	@Autowired
	private PostMapper mapper;
	
	public List<PostDTO> getAllPosts(){
		return repo.findAll()
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

	public List<PostDTO> getPostsByTag(String tag) {
		return repo.findByTagsName(tag)
				.stream()
				.map(mapper::toDTO)
				.toList();
	}
	
	public PostDTO create(CreatePostDTO dto) {
		
		Post post = new Post();
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		
		User user = repoUser.findById(dto.getUserId()).orElseThrow();
		post.setUser(user);
		
		Set<Tag> tags = new HashSet<>(repoTag.findAllById(dto.getTagIds()));
		post.setTags(tags);
		
		Post saved = repo.save(post);
		return mapper.toDTO(saved);
	}
}
