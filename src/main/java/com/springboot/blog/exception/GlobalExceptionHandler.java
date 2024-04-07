package com.springboot.blog.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.springboot.blog.payload.ErroDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	//handle Custom Exception 
	
	
	@ExceptionHandler(ResourceNotFoundExecption.class)
	public ResponseEntity<ErroDetails> handleResourceNotFoundExecption(ResourceNotFoundExecption execption 
			, WebRequest request){
		
		
		ErroDetails details = new ErroDetails(new Date(), execption.getMessage(), request.getDescription(false));
		
		
		return new ResponseEntity<ErroDetails>(details,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BlogAppException.class)
	public ResponseEntity<ErroDetails> handleBlogAppException(BlogAppException execption 
			, WebRequest request){
		
		
		ErroDetails details = new ErroDetails(new Date(), execption.getMessage(), request.getDescription(false));
		
		
		return new ResponseEntity<ErroDetails>(details,HttpStatus.BAD_REQUEST);
	}
	
	
	//Global exception handler - change from default format to your custom format 
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroDetails> handleException(Exception execption 
			, WebRequest request){
		
		
		ErroDetails details = new ErroDetails(new Date(), execption.getMessage(), request.getDescription(false));
		
		
		return new ResponseEntity<ErroDetails>(details,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
