package com.blogapplication.springboot_blog.reposiories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.springboot_blog.dto.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

	
}
