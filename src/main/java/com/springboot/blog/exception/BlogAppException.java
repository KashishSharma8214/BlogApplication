package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException{

	
	private HttpStatus status;
	private String message;
	
	public BlogAppException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
	
}
