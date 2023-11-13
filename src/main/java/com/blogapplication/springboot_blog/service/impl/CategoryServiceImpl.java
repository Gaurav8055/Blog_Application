package com.blogapplication.springboot_blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.springboot_blog.dto.Category;
import com.blogapplication.springboot_blog.exception.ResourceNotFoundException;
import com.blogapplication.springboot_blog.playloads.Categoryload;
import com.blogapplication.springboot_blog.reposiories.CategoryRepo;
import com.blogapplication.springboot_blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Categoryload createCategory(Categoryload categoryload) {
		Category category =   this.modelMapper.map(categoryload, Category.class);
		Category saveCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(saveCategory, Categoryload.class);
	}

	@Override
	public Categoryload updateCategory(Categoryload categoryload, Integer categoryId) {
	
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		category.setCategoryTitle(categoryload.getCategoryTitle());
		category.setCategoryDescription(categoryload.getCategoryDescription());
		
	Category updatedCategory = 	this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory, Categoryload.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CaategoryId", categoryId));
       
		this.categoryRepo.delete(category);
	}

	@Override
	public Categoryload getCategoryById(Integer categoryId) {
		 Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		 
		return this.modelMapper.map(category, Categoryload.class);
	}

	@Override
	public List<Categoryload> getAllCategories() {
	List<Category> categories = this.categoryRepo.findAll();
	
	 List<Categoryload> categoryloads =  categories.stream().map((category)-> this.modelMapper.map(category, Categoryload.class)).collect(Collectors.toList());
		return categoryloads;
	}

}
