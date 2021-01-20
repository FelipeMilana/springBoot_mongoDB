package com.javaudemy.springBoot_mongoDB.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaudemy.springBoot_mongoDB.domain.Post;
import com.javaudemy.springBoot_mongoDB.domain.User;
import com.javaudemy.springBoot_mongoDB.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		//verification if this id exist or not
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(String id, User obj) {
		//return original User from database with this id
		User newObj = findById(id); 
		//update other fields from obj to newObj
		updateData(newObj, obj);
		//save newObj updated
		return repository.save(newObj);
	}
	
	public void updatePostList(Post post) {
		User user = findById(post.getAuthor().getId());
		user.getPosts().add(post);
		repository.save(user);
	}
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	//can be in UserDTO
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}