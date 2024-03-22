package com.springboot.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Services.PostService;
import com.springboot.blog.payload.postDTO;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService= postService;
	}

	// Post Api to Post the data to DB 
	@PostMapping
	public ResponseEntity<postDTO> createPost(@RequestBody postDTO dto){
		return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
	}
	
	//Get Api to Get the data from The DB 
	@GetMapping
	public List<postDTO> getAllPosts(){
		List<postDTO> getallposts = postService.getallposts();
		return getallposts;
	}
	
	
	// Rest Api by passing the Id 
	@GetMapping("/{id}")
	public ResponseEntity<postDTO> getPostById(@PathVariable(name="id")long id ){
		return ResponseEntity.ok(postService.getPostByID(id));
	}
	
	
	//Update the Existing post rest api By id 
	
	@PutMapping("/{id}")
	public ResponseEntity<postDTO> UpdatePost(@RequestBody postDTO dto , @PathVariable(name="id")long id ){
		
		
		
		return new ResponseEntity<postDTO>(postService.UpdatePost(dto, id),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> DeletePost(@PathVariable(name="id")long id ){
		postService.DeletePost(id);
	
	return new ResponseEntity<String>("The Post deleted Successfully ",HttpStatus.OK);
	
	}
	
}
