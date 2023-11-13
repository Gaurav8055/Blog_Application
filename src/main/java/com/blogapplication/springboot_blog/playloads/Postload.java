package com.blogapplication.springboot_blog.playloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import com.blogapplication.springboot_blog.dto.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@NoArgsConstructor
public class Postload {
	
	private Integer postId;
	
//	@NotBlank(message = "Title Can't Be Blank")
//	@NotNull(message = "Title Can'T Be Null")
	private  String title;
	

//	@NotBlank(message = "Content Cant't Be Blank")
//	@NotNull(message = "content Can't Be Null")
	
	private String content;
	
	private String imageName;
	private Date addDate;
	
	private Categoryload category;
	
	private Userload user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	
	
	

	

}
