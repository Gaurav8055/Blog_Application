package com.blogapplication.springboot_blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapplication.springboot_blog.util.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseStructure>ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ResponseStructure responseStructure = new ResponseStructure(message,false);
		return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
		Map<String, String> resp = new HashMap<>();
		
	  ex.getBindingResult().getAllErrors().forEach((error)->{
		   String fielName =  ((FieldError)error).getField();
		   
		    String message = error.getDefaultMessage();
		    
		    resp.put(fielName, message);
	  });
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

}
