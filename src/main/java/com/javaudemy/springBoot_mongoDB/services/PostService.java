package com.javaudemy.springBoot_mongoDB.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaudemy.springBoot_mongoDB.domain.Post;
import com.javaudemy.springBoot_mongoDB.repositories.PostRepository;
import com.javaudemy.springBoot_mongoDB.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;

	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> opt = repository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public Post insert(Post obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		//verification if this id exist or not
		findById(id);
		repository.deleteById(id);
	}
	
	public Post update(String id, Post obj) {
		//return original Post from database with this id
		Post newObj = findById(id); 
		//update other fields from obj to newObj
		updateData(newObj, obj);
		//save newObj updated
		return repository.save(newObj);
	}
	
	private void updateData(Post newObj, Post obj) {
		newObj.setBody(obj.getBody());
		newObj.setTitle(obj.getTitle());
		newObj.setDate(Instant.now());
	}
}