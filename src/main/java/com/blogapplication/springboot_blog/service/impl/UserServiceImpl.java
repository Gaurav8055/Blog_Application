package com.blogapplication.springboot_blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.springboot_blog.dto.User;
import com.blogapplication.springboot_blog.exception.ResourceNotFoundException;
import com.blogapplication.springboot_blog.playloads.Userload;
import com.blogapplication.springboot_blog.reposiories.UserRepo;
import com.blogapplication.springboot_blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Userload createUser(Userload userload) {
		
		User user = this.loadToUser(userload);
	
	   User saveUser = this.userRepo.save(user);
		return this.userTolUserload(saveUser);
	}

	@Override
	public Userload userupdate(Userload userload, Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User" , "id",userId));
		   
		user.setName(userload.getName());
		user.setEmail(userload.getEmail());
		user.setPassword(userload.getPassword());
		user.setAbout(userload.getAbout());
		
		User updaUser = this.userRepo.save(user);
		Userload userload1 = this.userTolUserload(updaUser);
		
		         
		return userload1;
	}

	@Override
	public Userload getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
				return this.userTolUserload(user);
	}

	@Override
	public List<Userload> getAllUsers() {
		List<User> users =	this.userRepo.findAll();
		
		List<Userload> userloads =	users.stream().map(user -> this.userTolUserload(user)).collect(Collectors.toList());

		    return userloads;
	}

	@Override
	public void deleteUser(Integer userId) {
	User user = 	this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
	
	this.userRepo.delete(user);
	}
	private User loadToUser(Userload userload)
	{
		
		User user = this.modelMapper.map(userload, User.class);
//		User user = new User();
//		user.setId(userload.getId());
//		user.setName(userload.getName());
//		user.setEmail(userload.getEmail());
//		user.setAbout(userload.getAbout());
//		user.setPassword(userload.getPassword());
		
		return user;
		
	}
	
	public Userload userTolUserload(User user)
	{
		Userload userload = this.modelMapper.map(user, Userload.class);
		return userload;
	}

}
