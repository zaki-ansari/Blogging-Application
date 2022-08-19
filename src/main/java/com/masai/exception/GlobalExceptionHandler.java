package com.masai.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.masai.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String msg = ex.getMessage();
		return new ResponseEntity(new ApiResponse(msg,false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
		Map<String,String> hm = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((er)->{
			String fieldName = ((FieldError)er).getField();
			String msg = er.getDefaultMessage();
			
			hm.put(fieldName, msg);
		});
		
		return new ResponseEntity<Map<String,String>>(hm,HttpStatus.BAD_REQUEST);
	}

}
