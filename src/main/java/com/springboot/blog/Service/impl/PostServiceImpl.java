package com.springboot.blog.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.blog.Entity.Post;
import com.springboot.blog.Services.PostService;
import com.springboot.blog.exception.ResourceNotFoundExecption;
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
		Post post= MaptoEntity(postDTO);
		
		Post save = postRespositoy.save(post);
		
		//Convert Entity to DTO 
		
		postDTO dto = MapToDTo(save);
		return dto;
	}


	
	//Method to get all the posts from the DB 
	@Override
	public List<postDTO> getallposts() {
		List<Post> findAll = postRespositoy.findAll();
		List<postDTO> collect = findAll.stream().map(post->MapToDTo(post)).collect(Collectors.toList());
		
		return collect;
	}
	
	
	// Convert DTO to Entity 
	private Post MaptoEntity(postDTO postDTO) {
		Post post = new Post();
		post.setId(postDTO.getId());
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		return post;
	}
	
	
	
	//Convert Entity to DTO 
	
	private postDTO MapToDTo(Post post) {
		
		postDTO dto = new postDTO();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setDescription(post.getDescription());
		dto.setContent(post.getContent());
		
		return dto;
	}


	@Override
	public postDTO getPostByID(long id) {
		Post post = postRespositoy.findById(id).orElseThrow(()-> new ResourceNotFoundExecption("Post", "id", id));
		
		
		return MapToDTo(post);
	}


	
	

	@Override
	public postDTO UpdatePost(postDTO postDTO, long id) {
	
		Post post = postRespositoy.findById(id).orElseThrow(()-> new ResourceNotFoundExecption("Post", "id", id));
		
		post.setContent(postDTO.getContent());
		post.setDescription(postDTO.getDescription());
		post.setTitle(postDTO.getTitle());
		Post save = postRespositoy.save(post);
		
		return MapToDTo(save);
	}


	@Override
	public void DeletePost(long id) {
		Post post = postRespositoy.findById(id).orElseThrow(()-> new ResourceNotFoundExecption("Post", "id", id));
		postRespositoy.delete(post);
		
		
	}
	

}
