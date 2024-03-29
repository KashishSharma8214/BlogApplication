package com.springboot.blog.payload;

import java.util.List;

import com.springboot.blog.Entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
	
	
	private List<postDTO> content;
	private int PageNo;
	private int PageSize;
	private long totalElements;
	private int totalpages;
	private boolean Last;
	
	public PostResponse() {
		
	}
	
	public PostResponse(List<postDTO> content, int pageNo, int pageSize, long totalElements, int totalpages,
			boolean last) {
		super();
		this.content = content;
		PageNo = pageNo;
		PageSize = pageSize;
		this.totalElements = totalElements;
		this.totalpages = totalpages;
		Last = last;
	}

	

	public int getPageNo() {
		return PageNo;
	}

	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}

	public boolean isLast() {
		return Last;
	}

	public void setLast(boolean last) {
		Last = last;
	}

	@Override
	public String toString() {
		return "PostResponse [content=" + content + ", PageNo=" + PageNo + ", PageSize=" + PageSize + ", totalElements="
				+ totalElements + ", totalpages=" + totalpages + ", Last=" + Last + "]";
	}

	public List<postDTO> getContent() {
		return content;
	}

	public void setContent(List<postDTO> content) {
		this.content = content;
	}

	
	
	
	

}
