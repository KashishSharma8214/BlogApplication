package com.springboot.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.FieldError;

import com.springboot.blog.payload.ErroDetails;

@ControllerAdvice
public class GlobalExceptionHandler  {
	
	
	
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
	
	
	
	// Customizing the exception to client format 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleResourceNotFoundExecption(MethodArgumentNotValidException execption 
			, WebRequest request){
		
		
		Map<String, String> errors = new HashMap<>();
		 List<ObjectError> allErrors = execption.getBindingResult().getAllErrors();
			
		allErrors.stream().forEach(error -> {
			String field = ((FieldError)(error)).getField();
			String defaultMessage = error.getDefaultMessage();
			errors.put(field, defaultMessage);
		});
		
		
	
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}

}
