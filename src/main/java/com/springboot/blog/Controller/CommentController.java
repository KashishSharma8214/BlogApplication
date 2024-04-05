package com.springboot.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Service.impl.CommentServiceImpl;
import com.springboot.blog.payload.CommentDTO;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	
	private CommentServiceImpl commentServiceImpl;
	
	public CommentController(CommentServiceImpl commentServiceImpl) {
	
		this.commentServiceImpl = commentServiceImpl;
	}

	@PostMapping("/posts/{postid}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(value="postid")long postid  , 
			@RequestBody CommentDTO commentDTO){
		
		
				return new ResponseEntity<CommentDTO>(commentServiceImpl.
						createComment(postid, commentDTO),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/posts/{postid}/comments")
	
	public List<CommentDTO> getallCommentsByPostId(@PathVariable(value="postid")long id ){
		
		
		return commentServiceImpl.getallComments(id);
	}
	
}
