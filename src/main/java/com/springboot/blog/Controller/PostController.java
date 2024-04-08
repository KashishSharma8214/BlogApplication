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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Services.PostService;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.payload.postDTO;
import com.springboot.blog.utils.AppConstants;

import jakarta.validation.Valid;

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
	public ResponseEntity<postDTO> createPost(@Valid @RequestBody postDTO dto){
		return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
	}
	
	//Get Api to Get the data from The DB 
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value="PageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required =false) int PageNo,
			@RequestParam(value="PageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required =false) int PageSize,
			@RequestParam(value="SortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String SortBy,
			@RequestParam(value="SortDir" , defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			){
		PostResponse getallposts = postService.getallposts(PageNo , PageSize,SortBy,sortDir);
		return getallposts;
	}
	
	
	// Rest Api by passing the Id 
	@GetMapping("/{id}")
	public ResponseEntity<postDTO> getPostById(@PathVariable(name="id")long id ){
		return ResponseEntity.ok(postService.getPostByID(id));
	}
	
	
	//Update the Existing post rest api By id 
	// @valid enable the validater to the Update API 
	@PutMapping("/{id}")
	public ResponseEntity<postDTO> UpdatePost(@Valid  @RequestBody postDTO dto , @PathVariable(name="id")long id ){
		
		
		
		return new ResponseEntity<postDTO>(postService.UpdatePost(dto, id),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> DeletePost(@PathVariable(name="id")long id ){
		postService.DeletePost(id);
	
	return new ResponseEntity<String>("The Post deleted Successfully ",HttpStatus.OK);
	
	}
	
}
