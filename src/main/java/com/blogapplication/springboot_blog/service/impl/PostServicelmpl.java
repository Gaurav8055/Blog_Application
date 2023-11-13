package com.blogapplication.springboot_blog.service.impl;

import java.util.Date;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapplication.springboot_blog.dto.Category;
import com.blogapplication.springboot_blog.dto.Post;
import com.blogapplication.springboot_blog.dto.User;
import com.blogapplication.springboot_blog.exception.ResourceNotFoundException;
import com.blogapplication.springboot_blog.playloads.PostResponse;
import com.blogapplication.springboot_blog.playloads.Postload;
import com.blogapplication.springboot_blog.reposiories.CategoryRepo;
import com.blogapplication.springboot_blog.reposiories.PostRepo;
import com.blogapplication.springboot_blog.reposiories.UserRepo;
import com.blogapplication.springboot_blog.service.PostService;


@Service
public class PostServicelmpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Postload createPost(Postload postload , Integer userId , Integer categoryId){
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "User Id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
	Post post =this.modelMapper.map(postload, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
	Post newPost = 	this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, Postload.class);
	}

	@Override
	public Postload updatePost(Postload postload, Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		 post.setTitle(postload.getTitle());
		post.setContent(postload.getContent());
		post.setImageName(postload.getImageName());
//		post.setCategory(postload.getCategory());
		
	Post updatedPost = 	this.postRepo.save(post);
		return modelMapper.map(updatedPost, Postload.class);
	}

	@Override
	public void deletePost(Integer postId) {
	Post post =	 this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(post); 

	}
	
	
	
//	@Override
//	public List<Postload> getAllPost() {
//		List<Post> allPosts = this.postRepo.findAll();
//		List<Postload> postLoads = allPosts.stream().map((post) -> this.modelMapper.map(post, Postload.class)).collect(Collectors.toList());
//		
//		return postLoads;
//	}

	@Override
	public PostResponse getAllPost(Integer pageNumber , Integer pageSize , String sortBy , String sortDir)  {
		 
		Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		
		
//		Sort sort = null;
//		if(sortDir.equalsIgnoreCase("asc"))
//		{
//			sort = Sort.by(sortBy).ascending();
//		}
//		else
//		{
//			sort = Sort.by(sortBy).descending();
//		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize , sort);
		
		
		Page<Post> pagePost =  this.postRepo.findAll(p);
		
	List<Post> allPosts  = 	pagePost.getContent() ;

	List<Postload> postlods = allPosts.stream().map((post)-> this.modelMapper.map(post, Postload.class)).collect(Collectors.toList());
	
	PostResponse postResponse = new PostResponse();
	postResponse.setContent(postlods);
   postResponse.setPageNumber(pagePost.getNumber());
   postResponse.setPageSize(pagePost.getSize());
   postResponse.setTotalElement(pagePost.getTotalElements());
   postResponse.setTotalPage(pagePost.getTotalPages());
   postResponse.setLastPage(pagePost.isLast());
	
		return postResponse;
	}

	@Override
	public Postload getPostById(Integer postId) {
	 Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
	 return modelMapper.map(post, Postload.class);
	}

	@Override
	public List<Postload> getPostByCategory(Integer categoryId) {
	Category cat = 	this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
	List<Post> posts = 	this.postRepo.findByCategory(cat);
	
    List<Postload> postLoads =  posts.stream().map((post)-> this.modelMapper.map(post, Postload.class)).collect(Collectors.toList());
	
	return
		postLoads;
		
	}

	@Override
	public List<Postload> getPostByUser(Integer userId) {
		
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<Postload> postLoads = posts.stream().map((post)-> this.modelMapper.map(post, Postload.class)).collect(Collectors.toList());
		
		return postLoads;
	}

	@Override
	public List<Postload> searchPosts(String Keyword) {
   
    List<Post> posts =   this.postRepo.findByTitleContaining(Keyword);
    
  List<Postload> postloads =   posts.stream().map((post) -> this.modelMapper.map(post, Postload.class)).collect(Collectors.toList());
		return postloads ;
	}

}
