package com.springboot.blog.Services;

import java.util.List;

import com.springboot.blog.payload.postDTO;

public interface PostService {
	
	postDTO createPost(postDTO postDTO);

	
	
	List<postDTO> getallposts();
}
