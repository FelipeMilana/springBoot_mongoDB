package com.javaudemy.springBoot_mongoDB.dto;

import java.io.Serializable;
import java.time.Instant;

public class CommentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//attributes
	private String text;
	private Instant  date;
	
	//associations
	private AuthorDTO author;
	
	//standard constructor and with fields
	public CommentDTO() {
	}
	
	public CommentDTO(String text, AuthorDTO author) {
		this.text =  text;
		setDate(Instant.now());
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
}