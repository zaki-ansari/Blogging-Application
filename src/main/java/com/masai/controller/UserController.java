package com.masai.controller;

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

import com.masai.payload.ApiResponse;
import com.masai.payload.UserDto;
import com.masai.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Post - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createdUserDto = userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
	}
	
	//Put - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
	{
		UserDto updatedUserDto = userService.updateUser(userDto,uid);
		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);
	}
	
	//Delete - delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable() Integer userId)
	{
		userService.deleteUser(userId);
		//return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	//Get - get users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto>users = userService.getAllUsers();
		
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
	//Get - get user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uid)
	{
		
		return ResponseEntity.ok(userService.getUserById(uid));
	}
}
