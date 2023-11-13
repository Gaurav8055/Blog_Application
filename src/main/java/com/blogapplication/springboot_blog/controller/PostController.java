package com.blogapplication.springboot_blog.controller;

import java.io.IOException;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.springboot_blog.config.AppConstants;
import com.blogapplication.springboot_blog.playloads.PostResponse;
import com.blogapplication.springboot_blog.playloads.Postload;
import com.blogapplication.springboot_blog.service.FileService;
import com.blogapplication.springboot_blog.service.PostService;
import com.blogapplication.springboot_blog.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api")
public class PostController {
	
	
	@Autowired
	private PostService postService;
 
	@Autowired
	private FileService fileService;
	
	
      @Value("${project.image}")
	private String path;
	
	
	@ApiOperation(value = "Create Post", notes = "This Api Is Used For Save New Post in Database" )
	@ApiResponses(value = {@ApiResponse(code = 201 , message = "User Save Sucessfully")})
	@PostMapping("/user/{userId}/category/{categoryId}/posts/create")
	public ResponseEntity<Postload> createPost(
			@Valid 
			@RequestBody Postload postload ,
			@PathVariable Integer userId  , 
			@PathVariable Integer categoryId
			)
	{
	Postload createPost = this.postService.createPost(postload, userId , categoryId);
		
	return new ResponseEntity<Postload>(createPost,HttpStatus.CREATED);
	
	}
	
	
	//get  By User
    @ApiOperation(value = "Find Post By User" , notes = "This Api Is Used For Search Post By User")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Post Data Found Sucessfully"),@ApiResponse(code = 404 , message = "Can't Found Post")})
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<Postload>> getPostByUser(  @PathVariable Integer userId)
	{
	     List<Postload> posts = this.postService.getPostByUser(userId);
	     
	     return new ResponseEntity<List<Postload>>(posts , HttpStatus.OK);
	}
	
	
	//get By posts
	@ApiOperation(value = "Find Post By Catagory" , notes = "This Api is Used For Search Post By Catagory")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Post Found Sucessfully") , @ApiResponse(code = 404 , message = "Post Can't Found")})
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<Postload>> getPostsByCategory(@Valid @PathVariable Integer categoryId)
	{
		List<Postload> posts = this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<Postload>>(posts , HttpStatus.OK);
	}
	
	// get post by id
	@ApiOperation(value = "Find Post By Id" , notes = "This Api Is Used For Search Post By Id")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Post Found Sucessfully") , @ApiResponse(code = 404 , message = "Post Can't Found Sucessfully")})
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Postload> getPostById (@Valid @PathVariable Integer postId)
	{
		Postload postload  = this.postService.getPostById(postId);
		return  new ResponseEntity<Postload>(postload , HttpStatus.OK);
		
	}
	
	//get All post
	@ApiOperation(value = "Find All Posts" , notes = "This Api Is Used For Get All Post")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "All Post Found Sucessfully") , @ApiResponse(code = 404 , message = "No Post Present In Database")})
	@GetMapping("/meposts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE ,required = false) Integer pageSize,
			@RequestParam(value = "sortBy" , defaultValue = AppConstants.SORT_BY ,required = false) String sortBy,
			@RequestParam(value = "sortDir" , defaultValue = AppConstants.SORT_DIR , required = false) String sortDir
			)
	{
		 PostResponse postResponse = this.postService.getAllPost(pageNumber , pageSize , sortBy , sortDir);
		
		return  new ResponseEntity<PostResponse>(postResponse , HttpStatus.OK);
	}
	
	
	
	
	//delete
	@ApiOperation(value = "Delete Post" , notes = "This Api Is Used For Delete Post" )
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Post Delete Sucessfully") , @ApiResponse(code = 404 ,message = "Can't Delete Post")})
	@DeleteMapping("/delete/post/{postId}")
	public ResponseEntity<ResponseStructure> deletePost(@Valid @PathVariable Integer postId)
	{
		
		this.postService.deletePost(postId);
		return new ResponseEntity<ResponseStructure>(new ResponseStructure("Post Is Deleted Sucessfully !!" , true) , HttpStatus.OK);
	}
	
	
	// update Post
	@ApiOperation(value = "Update Post" , notes = "This Api Used For Update Post")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Pos Update Sucessfully") , @ApiResponse(code = 404 , message = "Can't Update Post")})
	@PutMapping("/update/post/{postId}")
	public ResponseEntity<Postload> updatePost(@RequestBody Postload postload ,  @PathVariable Integer postId)
	{
		Postload updatePost = this.postService.updatePost(postload, postId);
		
		return new ResponseEntity<Postload>(updatePost , HttpStatus.OK);
	}
	
	//search
	@ApiOperation(value = "Search Post By KeyWord" , notes = "This Api Is Used For Search Post")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Post Find Sucessfully") , @ApiResponse(code = 404 , message = "Post Not Find")})
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<Postload>> searchPostByTitle(@PathVariable("keywords") String keywords)
	{
	List<Postload> result = this.postService.searchPosts(keywords);
	return new ResponseEntity<List<Postload>>(result , HttpStatus.OK);
	}
	
	// post Image upload
	
	@ApiOperation(value = "Upload Image" , notes = "This Api Is Used For Upload Image")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Upload Image Sucessfully") , @ApiResponse(code = 404 , message = "Unable To Upload Image")})
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<Postload>uplodPostImage(@RequestParam MultipartFile image , @PathVariable Integer postId) throws IOException
	{
		  Postload postload =  this.postService.getPostById(postId);
		
		String fileName =  this.fileService.uploadImage(path, image);
		postload.setImageName(fileName);
		
	Postload updatedPosts = this.postService.updatePost(postload, postId);
		return new ResponseEntity<Postload>(updatedPosts,HttpStatus.OK);
	}
	
	
	// method to serve file
	@ApiOperation(value = "Downlod Or View Image" ,notes = "This Api Is Used To Download Or View Image")
	@ApiResponses(value = {@ApiResponse(code = 200 , message = "Image Serve Sucessfully") , @ApiResponse(code = 404 , message = "Can't Serve Image")})
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName ,HttpServletResponse response)
			throws IOException
	{


		
InputStream resource =	this.fileService.getResource(path, imageName);
               response.setContentType(MediaType.IMAGE_JPEG_VALUE);
               
               StreamUtils.copy(resource, response.getOutputStream());
		
	}	
	
	
	

}
