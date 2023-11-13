package com.blogapplication.springboot_blog.playloads;

import java.util.List;

import com.blogapplication.springboot_blog.playloads.Postload;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<Postload> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPage;
	
	private boolean lastPage;
	
	
	

}
