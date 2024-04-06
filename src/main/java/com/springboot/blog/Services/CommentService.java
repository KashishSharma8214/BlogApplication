package com.springboot.blog.Services;

import java.util.List;


import com.springboot.blog.payload.CommentDTO;

public interface CommentService {
	
	
	public CommentDTO createComment(long id , CommentDTO commentDTO);
	
	public List<CommentDTO> getallComments(long id);
	
	public CommentDTO getCommentByid(long postId, long commentid);
	
	public CommentDTO UpdateCommentByid(CommentDTO commentDTO ,Long postId , Long commentid);
	
	public String DeleteCommentById(Long postId , Long commentid);
	

}
