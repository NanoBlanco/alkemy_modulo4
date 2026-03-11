package com.reinaldo.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.blog.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByTagsName(String name);
}
