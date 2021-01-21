package com.javaudemy.springBoot_mongoDB.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//attributes
	private String text;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private Instant date;
	
	//associations
	private AuthorDTO author;
	
	//standard constructor and with fields
	public CommentDTO() {
	}
	
	public CommentDTO(String text, Instant date, AuthorDTO author) {
		this.text =  text;
		this.date = date;
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