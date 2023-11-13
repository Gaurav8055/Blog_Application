package com.blogapplication.springboot_blog.service;

import java.util.List;

import com.blogapplication.springboot_blog.playloads.Userload;

public interface UserService{
	
   Userload createUser(Userload userload);
	
	Userload userupdate(Userload userload , Integer userId);
	
	Userload getUserById(Integer userId);
	
	List<Userload> getAllUsers();
	
	void deleteUser(Integer userId);

}
