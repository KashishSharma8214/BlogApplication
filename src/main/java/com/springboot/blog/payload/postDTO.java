package com.springboot.blog.payload;

import java.util.HashSet;
import java.util.Set;

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
public class postDTO {
	
	private Long id;
	
	// title should have atleast 2 character 
	// title should not be null or empty
	@NotEmpty
	@Size(min = 2 , message ="title should have two characters")
	private String title;
	
	// description should have atleast 10 character 
	// description should not be null or empty
	@NotEmpty
	@Size(min=10, message ="Description Should have 10 characters")
	private String description;
	@NotEmpty
	private String content;
	
	private Set<CommentDTO> comments = new HashSet<>();
	
	
	public Set<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDTO> comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
