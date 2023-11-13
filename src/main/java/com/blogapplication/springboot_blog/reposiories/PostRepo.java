package com.blogapplication.springboot_blog.reposiories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.springboot_blog.dto.Category;
import com.blogapplication.springboot_blog.dto.Post;
import com.blogapplication.springboot_blog.dto.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	
	List<Post> findByTitleContaining(String title);//like 
	
	
}
