package com.springboot.blog.Service.impl;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.Entity.Post;
import com.springboot.blog.Services.PostService;
import com.springboot.blog.exception.ResourceNotFoundExecption;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.payload.postDTO;
import com.springboot.blog.repository.PostRespositoy;


@Service
public class PostServiceImpl implements PostService{

	
	private PostRespositoy postRespositoy;


	public PostServiceImpl(PostRespositoy postRespositoy) {
		this.postRespositoy=postRespositoy;
	}
	
	
	public PostResponse postResponse;
	
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
	public PostResponse getallposts(int PageNo, int PageSize,String SortBy) {
		
		
		// Creating the PageRequest object and Using "Of" static Method 
		PageRequest pageable =  PageRequest.of(PageNo, PageSize, Sort.by(SortBy));
		
		// Using findAll method of Pageable instance 
		Page<Post> findAll = postRespositoy.findAll(pageable);	
		
		// getting the content of Page and store that in a list 
		List<Post> content = findAll.getContent();
		
		List<postDTO> collect = content.stream().map(post->MapToDTo(post)).collect(Collectors.toList());
		
		
		//Creating the Instance of PostResponse Model class and setting the values 
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNo(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalpages(findAll.getTotalPages());
		postResponse.setLast(findAll.isLast());
		return postResponse;
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
