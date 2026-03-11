package com.reinaldo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.blog.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
