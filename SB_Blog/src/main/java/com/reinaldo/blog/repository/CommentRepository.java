package com.reinaldo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.blog.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
