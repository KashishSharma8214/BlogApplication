package com.springboot.blog.Services;

import java.util.List;

import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.payload.postDTO;

public interface PostService {
	
	public postDTO createPost(postDTO postDTO);

	
	
	public PostResponse getallposts(int PageNo , int PageSize,String SortBy, String sortDir);
	
	public postDTO getPostByID(long id);



	public postDTO UpdatePost(postDTO postDTO,long id);
	
	
	public void DeletePost(long id);
}
