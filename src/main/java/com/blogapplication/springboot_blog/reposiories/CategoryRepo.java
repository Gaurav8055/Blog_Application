package com.blogapplication.springboot_blog.reposiories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.springboot_blog.dto.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	

}
