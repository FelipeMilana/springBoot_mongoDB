package com.javaudemy.springBoot_mongoDB.dto;

import java.io.Serializable;

import com.javaudemy.springBoot_mongoDB.domain.User;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// attributes
	private String id;
	private String name;
	private String email;

	// constructors, standard and with arguments
	public UserDTO() {
	}

	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	// getters and setters methods
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}