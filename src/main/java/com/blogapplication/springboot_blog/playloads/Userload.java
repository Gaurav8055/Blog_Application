package com.blogapplication.springboot_blog.playloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Userload {

	private int id;
	
	@NotNull
	@NotEmpty
	@Size(min = 4 , message = "UserName Must be min 4 Character")
	private String name;
	
	@Email(message = "Your Given Email Address Not Valid")
	private String email;
	
	@NotEmpty
	@NotNull
	@Size(min = 3 , max = 10 , message = "Password Must Min 3 OR Max 10")
	private String password;
	
	@NotNull
	private String about;
	
	
	
}
