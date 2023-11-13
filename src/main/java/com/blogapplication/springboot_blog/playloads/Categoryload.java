package com.blogapplication.springboot_blog.playloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Categoryload {
	
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4 , message = "minimum size of category Title is 4 Word")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10 , message = "minimum size of category Description is 10 words")
	private String categoryDescription;
	
	
	

}
