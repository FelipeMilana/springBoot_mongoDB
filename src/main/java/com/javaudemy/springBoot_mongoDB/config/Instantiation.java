package com.javaudemy.springBoot_mongoDB.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.javaudemy.springBoot_mongoDB.domain.Post;
import com.javaudemy.springBoot_mongoDB.domain.User;
import com.javaudemy.springBoot_mongoDB.dto.AuthorDTO;
import com.javaudemy.springBoot_mongoDB.dto.CommentDTO;
import com.javaudemy.springBoot_mongoDB.repositories.PostRepository;
import com.javaudemy.springBoot_mongoDB.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		//to delete datas when springboot app is started
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User felipe = new User(null, "Felipe Milana", "felipe.milana@gmail.com");
		//need to save before, because this users needs to have an own id.
		userRepository.saveAll(Arrays.asList(maria, alex, bob, felipe));
		
		Post post1 = new Post(null,	Instant.parse("2018-03-21T00:00:00Z"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.parse("2018-03-23T00:00:00Z"), "Bom dia!", "Acordei feliz hoje", new AuthorDTO(maria));
		
		CommentDTO  c1  = new CommentDTO("Boa viagem mano", Instant.parse("2018-03-21T00:00:00Z"), new AuthorDTO(alex));
		CommentDTO  c2  = new CommentDTO("Aproveite", Instant.parse("2018-03-22T00:00:00Z"), new AuthorDTO(bob));
		CommentDTO  c3  = new CommentDTO("Tenha um ótimo dia", Instant.parse("2018-03-23T00:00:00Z"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		//verification if author and User is the same
		if(maria.getId().equals(post1.getAuthor().getId()) && maria.getId().equals(post2.getAuthor().getId())) {
			userRepository.save(maria);
		}
		
	}
}