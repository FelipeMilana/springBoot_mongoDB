package com.javaudemy.springBoot_mongoDB.resources;

import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javaudemy.springBoot_mongoDB.domain.Post;
import com.javaudemy.springBoot_mongoDB.resources.utils.URL;
import com.javaudemy.springBoot_mongoDB.services.PostService;
import com.javaudemy.springBoot_mongoDB.services.UserService;

@RestController 
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "")String text){
		text = URL.decodeParam(text);
		List<Post> posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "")String text,
											     @RequestParam(value = "minDate", defaultValue = "")String minDate,
			                                     @RequestParam(value = "maxDate", defaultValue = "")String maxDate){
		text = URL.decodeParam(text);
		Instant min = URL.convertStringtoDate(minDate, new Date(0L)).toInstant();
		Instant max = URL.convertStringtoDate(maxDate, new Date()).toInstant();
		
		List<Post> posts = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(posts);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Post post){
		post.setDate(Instant.now());
		Post obj = service.insert(post);
		userService.updatePostList(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post){
		Post obj = service.update(id, post);
		return ResponseEntity.ok().body(obj);
	}
	
}