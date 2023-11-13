package com.blogapplication.springboot_blog.controller;



import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.springboot_blog.playloads.Userload;
import com.blogapplication.springboot_blog.service.UserService;
import com.blogapplication.springboot_blog.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value=" Save User" , notes = "This Api Used To Save New User Into Database")
	@ApiResponses(value = {@ApiResponse(code = 201 , message = "User Save Sucessfully")})
	@PostMapping("/Create")
	public ResponseEntity<Userload> createUser(@Valid @RequestBody Userload userload)
	{
		Userload createUserLoad = this.userService.createUser(userload);
		
		return new ResponseEntity<>(createUserLoad , HttpStatus.CREATED);
	}
	
 	
	@ApiOperation(value = "Update User" , notes = "This Api Used For Update Value")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Update User Sucessfully") , @ApiResponse(code = 404 , message = "Can't Update User")})
	@PutMapping("/update/{userId}")
	public ResponseEntity<Userload> updateUser( @Valid @RequestBody Userload userload , @PathVariable Integer userId)
	{
	  Userload updatedUser =  this.userService.userupdate(userload, userId);	
	  
	  return ResponseEntity.ok(updatedUser);
	}
	
	@ApiOperation(value = "Delete User" , notes = "This Api Used For Delete User")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "User Deleted Sucessfully !!") , @ApiResponse(code = 404 , message = "Can't Delete User")})
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ResponseStructure>deleteUser(@PathVariable Integer userId)
	{
		this.userService.deleteUser(userId);
		return new  ResponseEntity<ResponseStructure>(new ResponseStructure("User deleted Sucessfully", true ), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find All Users" , notes   = "This Api Is Used Forn Find All Users" )
	@ApiResponses(value = {@ApiResponse(code = 302 , message = "Fetch Sucessfully")})
	@GetMapping("/allUsers")
	public ResponseEntity<List<Userload>>getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@ApiOperation(value = "Find User By Id" , notes = "This Api Used For Find User By Id")
	@ApiResponses(value = {@ApiResponse (code = 200 , message = "Find Sucessfully"), @ApiResponse(code = 404 , message = "Can't Find User")})
	@GetMapping
	("/{userId}")
	public ResponseEntity<Userload>getById(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	
	
	
	

}
