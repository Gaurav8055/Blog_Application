package com.blogapplication.springboot_blog.service;

import java.util.List;

import com.blogapplication.springboot_blog.playloads.PostResponse;
import com.blogapplication.springboot_blog.playloads.Postload;


public interface PostService {

  Postload createPost(Postload postload , Integer userId , Integer categoryId);
  
  Postload updatePost(Postload postload , Integer postId);
  
  void deletePost(Integer postId);
  
  PostResponse getAllPost(Integer pageNumber , Integer pageSize , String sortBy , String sortDir);
  
//     List<Postload> getAllPost();
  
   // Post By ID
  Postload getPostById(Integer postId);
  
  //get  all Post By Category
  List<Postload>getPostByCategory(Integer categoryId);
  
  //get all Post By User
  List<Postload>getPostByUser(Integer userId);
  
  //search POST
  
  List<Postload> searchPosts(String Keyword);


  
  
}
