package com.reinaldo.blog.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreatePostDTO {

	private String title;
	private String content;
	private Long userId;
	private List<Long> tagIds;
}
