package com.javaudemy.springBoot_mongoDB.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.javaudemy.springBoot_mongoDB.domain.Post;
import com.javaudemy.springBoot_mongoDB.domain.User;
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
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, Instant.parse("2018-08-21T00:00:00Z"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", maria);
		Post post2 = new Post(null, Instant.parse("2018-08-23T00:00:00Z"), "Bom dia!", "Acordei feliz hoje", maria);
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}