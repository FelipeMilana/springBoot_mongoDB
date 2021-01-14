package com.javaudemy.springBoot_mongoDB.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaudemy.springBoot_mongoDB.domain.User;
import com.javaudemy.springBoot_mongoDB.repositories.UserRepository;
import com.javaudemy.springBoot_mongoDB.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> opt = repository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
}
