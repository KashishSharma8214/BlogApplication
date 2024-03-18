package com.springboot.blog.Service.impl;

import org.springframework.stereotype.Service;

import com.springboot.blog.Entity.Post;
import com.springboot.blog.Services.PostService;
import com.springboot.blog.payload.postDTO;
import com.springboot.blog.repository.PostRespositoy;


@Service
public class PostServiceImpl implements PostService{

	
	private PostRespositoy postRespositoy;


	public PostServiceImpl(PostRespositoy postRespositoy) {
		this.postRespositoy=postRespositoy;
	}
	
	
	@Override
	public postDTO createPost(postDTO postDTO) {
		// TODO Auto-generated method stub
		
		//convert Dto to Entity
		Post post= new Post();
		post.setId(postDTO.getId());
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
			
		Post save = postRespositoy.save(post);
		
		//Convert Entity to DTO 
		
		postDTO dto = new postDTO();
		dto.setId(save.getId());
		dto.setTitle(save.getTitle());
		dto.setDescription(save.getDescription());
		dto.setContent(save.getContent());
		return dto;
	}

}
