package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExecption extends RuntimeException{
	
	
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	public ResourceNotFoundExecption(String resourceName, String fieldName, long id) {
		
		
		super(String.format(" %s Not found with %s : %s",resourceName,fieldName,id));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = id;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public long getFieldValue() {
		return fieldValue;
	}
	
	

}
