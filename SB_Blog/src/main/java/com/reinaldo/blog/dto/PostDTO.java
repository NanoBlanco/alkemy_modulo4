package com.reinaldo.blog.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
	
	private Long id;
	private String title;
	private String content;
	private String author;
	private List<String> tags;

}
