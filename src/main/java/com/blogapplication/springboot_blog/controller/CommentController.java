package com.blogapplication.springboot_blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.springboot_blog.playloads.CommentDto;
import com.blogapplication.springboot_blog.service.CommentService;
import com.blogapplication.springboot_blog.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api")
public class CommentController {
	
	
	@Autowired 
	private CommentService commentService;
	
	
    @ApiOperation(value = "Create Comment " , notes = "This Api Is Used For Create Comment ")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Cteate Comment Sucessfully") , @ApiResponse(code = 404 , message = "Can't Create CommeSnt")})
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable Integer postId)
	{
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	
    @ApiOperation(value = "Delete Comment" , notes = "This Api Is Used For Delete Comment ")
    @ApiResponses(value = {@ApiResponse(code = 200 , message = "Delete Comment Sucessfully") , @ApiResponse(code = 404 , message = "Can't Delete Comment")})
	@DeleteMapping("/comments/delete/{commentId}")
	public ResponseEntity<ResponseStructure> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ResponseStructure>(new ResponseStructure("Comment Deleted Sucessfully !!" , true),HttpStatus.OK);
	}
	
	
	
}
