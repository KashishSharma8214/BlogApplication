package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	
	private long id; 
	
	@NotEmpty(message ="Name should not be Empty")
	private String name;
	
	@NotEmpty(message = "Email Should not be null or empty")
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 10 , message = "Body atleat have 10 characters")
	private String body;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", name=" + name + ", email=" + email + ", body=" + body + "]";
	}

	public CommentDTO() {
		
	}
	
}
