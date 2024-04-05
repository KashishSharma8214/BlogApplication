package com.springboot.blog.Services;

import java.util.List;

import com.springboot.blog.Entity.Comment;
import com.springboot.blog.payload.CommentDTO;

public interface CommentService {
	
	
	public CommentDTO createComment(long id , CommentDTO commentDTO);
	
	public List<CommentDTO> getallComments(long id);

}
