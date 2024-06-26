package com.springboot.blog.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.Entity.Comment;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.Services.CommentService;
import com.springboot.blog.exception.BlogAppException;
import com.springboot.blog.exception.ResourceNotFoundExecption;
import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRespositoy;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	public CommentRepository commentRepository;
	
	@Autowired
	public PostRespositoy postRespositoy;
	
	@Autowired
	public ModelMapper mapper;

	@Override
	public CommentDTO createComment(long postid, CommentDTO commentDTO) {
		
		//Mapping Dto to entity 
		Comment comment = MaptoEntity(commentDTO);
		
		
		//reterive Post by id 
		Post post = postRespositoy.findById(postid).
				orElseThrow(()-> new ResourceNotFoundExecption("Post", "id", postid));
		
		//Set post to comment Entity  
		comment.setPost(post);
		
		//comment entity to DB 
		Comment comment2=  commentRepository.save(comment);
		
		
		return  MaptoDTO(comment2);
	}
	
	
	private CommentDTO MaptoDTO(Comment comment) {
		
		CommentDTO commentDTO= mapper.map(comment, CommentDTO.class);
		
//		CommentDTO commentDTO = new CommentDTO();
//		commentDTO.setId(comment.getId());
//		commentDTO.setBody(comment.getBody());
//		commentDTO.setEmail(comment.getEmail());
//		commentDTO.setName(comment.getName());
		
		
		return commentDTO;
		
		
		
	}
	
	private Comment MaptoEntity(CommentDTO commentDTO) {
		
		Comment  comment= mapper.map(commentDTO, Comment.class);
//		Comment  comment = new Comment();
//		comment.setId(commentDTO.getId());
//		comment.setBody(commentDTO.getBody());
//		comment.setEmail(commentDTO.getEmail());
//		comment.setName(commentDTO.getName());
		return comment;
	}


	@Override
	public List<CommentDTO> getallComments(long id) {
		
		//retrive comments by postid 
		List<Comment> findByPostId = commentRepository.findByPostId(id);
		
		//convert list of comment entities to List of Comment dto's
		return findByPostId.stream().map(t -> MaptoDTO(t)).collect(Collectors.toList());
	}


	@Override
	public CommentDTO getCommentByid(long postId, long commentid) {
		
		Post post = postRespositoy.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundExecption("Post", "id", postId));
		
		Comment comment = commentRepository.findById(commentid).
		orElseThrow(()-> new ResourceNotFoundExecption("Comment", "id", commentid));
		
		
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Comment does not belong to the Post");
		}
		
		
		return MaptoDTO(comment);
	}


	
	
	@Override
	public CommentDTO UpdateCommentByid(CommentDTO commentDTO ,Long postId , Long commentid) {
		
		
		// reterive the post by id 
		Post post = postRespositoy.findById(postId).
				orElseThrow(()-> new ResourceNotFoundExecption("Post", "id ", postId));
		
		
		
		
		Comment comment = commentRepository.findById(commentid).
				orElseThrow(()-> new ResourceNotFoundExecption("Comment", "id", commentid));
		
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Comment does not belong to the Post");
		}
			
		
		
		comment.setBody(commentDTO.getBody());
		comment.setEmail(commentDTO.getEmail());
		comment.setName(commentDTO.getName());

		Comment save = commentRepository.save(comment);
		
		
		return MaptoDTO(save);
	
		
	}


	@Override
	public String DeleteCommentById( Long postId, Long commentid) {
		
		// reterive the post by id 
				Post post = postRespositoy.findById(postId).
						orElseThrow(()-> new ResourceNotFoundExecption("Post", "id ", postId));
				
				
				
				
				Comment comment = commentRepository.findById(commentid).
						orElseThrow(()-> new ResourceNotFoundExecption("Comment", "id", commentid));
				
				
				if(!comment.getPost().getId().equals(post.getId())) {
					throw new BlogAppException(HttpStatus.BAD_REQUEST, "Comment does not belong to the Post");
				}
		
				commentRepository.deleteById(commentid);
				
		return "Comment Deleted Successfully With id :" +commentid;
	}


	
}
