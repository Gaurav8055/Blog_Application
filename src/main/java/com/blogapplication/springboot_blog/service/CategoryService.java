package com.blogapplication.springboot_blog.service;

import java.util.List;

import com.blogapplication.springboot_blog.playloads.Categoryload;

public interface CategoryService {
	
	//create
	 Categoryload createCategory(Categoryload categoryload);
	
	//update
	 Categoryload updateCategory(Categoryload categoryload , Integer categoryId);
	
	//delete
	 void deleteCategory(Integer categoryId);
	
	//find
	 Categoryload getCategoryById(Integer categoryId);
	
	//findAll
	 List<Categoryload> getAllCategories();

}
