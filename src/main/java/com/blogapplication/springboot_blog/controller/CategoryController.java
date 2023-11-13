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

import com.blogapplication.springboot_blog.playloads.Categoryload;
import com.blogapplication.springboot_blog.service.CategoryService;
import com.blogapplication.springboot_blog.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@ApiOperation(value = "Create Catagory" , notes = "This Api Is Used For Create New Catagory")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Catagory Create Sucessfully") , @ApiResponse(code = 404 , message = "Cant Create Catagory")})
	@PostMapping("/create")
	public ResponseEntity<Categoryload> createCategory(@Valid @RequestBody Categoryload categoryload)
	{
		Categoryload createCategory =  this.categoryService.createCategory(categoryload);
		
		return new ResponseEntity<Categoryload>(createCategory , HttpStatus.CREATED);
	}
	
	//update
	@ApiOperation(value = "Update Catagory" , notes = "This Api Is Used For Update  Catagory")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Catagory Update Sucessfully") , @ApiResponse(code = 404 , message = "Cant Update Catagory")})
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<Categoryload> updateCategory(@Valid @RequestBody Categoryload categoryload , @PathVariable Integer categoryId)
	{
		Categoryload updateCategory =  this.categoryService.updateCategory(categoryload , categoryId);
		
		return new ResponseEntity<Categoryload>(updateCategory , HttpStatus.OK);
	}
	//delete
	@ApiOperation(value = "Delete Catagory" , notes = "This Api Is Used For Delete  Catagory")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Catagory Delete Sucessfully") , @ApiResponse(code = 404 , message = "Cant Delete Catagory")})
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ResponseStructure> deleteCategory(@Valid @PathVariable Integer categoryId)
	{
		  this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ResponseStructure>(new ResponseStructure("Categories Is Deleted Sucessfully" , true) , HttpStatus.OK);
	}
	
	//find 
	@ApiOperation(value = "Find Catagory" , notes = "This Api Is Used For Find  Catagory By Id")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Catagory Found Sucessfully") , @ApiResponse(code = 404 , message = "Cant Found Catagory")})
	@GetMapping("/find/{categoryId}")
	public ResponseEntity<Categoryload> findCategory(@PathVariable Integer categoryId)
	{
				  return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}
	
	
	
	//Find All
	@ApiOperation(value = "Find All Catagory" , notes = "This Api Is Used For Find All  Catagory")
	@ApiResponses(value = {@ApiResponse(code = 302 , message = " All Catagory Found Sucessfully") , @ApiResponse(code = 404 , message = "Cant  Found  Catagory")})
	@GetMapping("/findall")
	public ResponseEntity<List<Categoryload>> findAllCategories()
	{
		List<Categoryload> categories = this.categoryService.getAllCategories();
				return ResponseEntity.ok(categories);
				
	}
	
	
}
