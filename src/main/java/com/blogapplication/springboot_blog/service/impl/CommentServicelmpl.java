package com.blogapplication.springboot_blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.springboot_blog.dto.Comment;
import com.blogapplication.springboot_blog.dto.Post;
import com.blogapplication.springboot_blog.exception.ResourceNotFoundException;
import com.blogapplication.springboot_blog.playloads.CommentDto;
import com.blogapplication.springboot_blog.reposiories.CommentRepo;
import com.blogapplication.springboot_blog.reposiories.PostRepo;
import com.blogapplication.springboot_blog.service.CommentService;

@Service
public class CommentServicelmpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post_Id", postId));
		    Comment comment = this.modelMapper.map(commentDto, Comment.class);
		             comment.setPost(post);
		             
		 Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		 Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));
		 
		 this.commentRepo.delete(com);

	}

}
