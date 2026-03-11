package com.reinaldo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.blog.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
