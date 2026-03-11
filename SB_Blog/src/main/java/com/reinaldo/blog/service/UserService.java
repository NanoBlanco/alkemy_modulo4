package com.reinaldo.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reinaldo.blog.domain.User;
import com.reinaldo.blog.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	
	public User create(User u) {
		return repo.save(u);
	}
}
