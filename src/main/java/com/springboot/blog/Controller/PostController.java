package com.springboot.blog.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Services.PostService;
import com.springboot.blog.payload.postDTO;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {
	
	
	
	
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService= postService;
	}

	
	@PostMapping
	public ResponseEntity<postDTO> createPost(@RequestBody postDTO dto){
		return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
	}
	
	
}
