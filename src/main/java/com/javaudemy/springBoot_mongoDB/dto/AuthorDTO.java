package com.javaudemy.springBoot_mongoDB.dto;

import java.io.Serializable;

import com.javaudemy.springBoot_mongoDB.domain.User;

public class AuthorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//attributes
	private String id;
	private String name;
	
	//standard constructor and with fields
	public AuthorDTO() {
	}
	
	public AuthorDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	//getters and setters methods
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
}