package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entity.Post;



public interface PostRespositoy extends JpaRepository<Post, Long>{

}
