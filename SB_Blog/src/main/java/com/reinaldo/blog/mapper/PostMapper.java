package com.reinaldo.blog.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.reinaldo.blog.domain.Post;
import com.reinaldo.blog.domain.Tag;
import com.reinaldo.blog.dto.CreatePostDTO;
import com.reinaldo.blog.dto.PostDTO;

@Component
public class PostMapper {

	public PostDTO toDTO(Post post) {
		
		PostDTO dto = new PostDTO();
		
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		
		if(post.getUser()!=null)
			dto.setAuthor(post.getUser().getUsername());
		
		List<String> tags = post.getTags()
				.stream()
				.map(Tag::getName)
				.toList();
		return dto;
	}
	
}
