package com.blogapplication.springboot_blog.dto;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;




import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Name Cant't be Null")
	@NotBlank(message = "Name Can't Be Blan'k")
	private String name;
	
	@Column(unique = true)
	@NotNull(message = "Email Can't Be Null")
	@NotBlank(message = "Email Cant Be Blank")
	@Email(regexp =  "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email Pattern")
	private String email;
	
	@NotNull(message = "Password Can't Be Null")
	@NotBlank(message = "Password Can't Be Blank")
	
	private String password;
	
	private String about;
	
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name="user_role" , 
	joinColumns = @JoinColumn(name="user" , referencedColumnName = "id"), inverseJoinColumns =  @JoinColumn(name="role" , referencedColumnName = "id") )
	private Set<Role> roles = new HashSet<>();





	
	
	

}
