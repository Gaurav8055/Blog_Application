package com.blogapplication.springboot_blog.service;

import com.blogapplication.springboot_blog.playloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment (CommentDto commentDto , Integer postId);
	
	void deleteComment(Integer commentId);

}
