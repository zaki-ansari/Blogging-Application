package com.masai.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.masai.config.AppConstants;
import com.masai.payload.ApiResponse;
import com.masai.payload.PostDto;
import com.masai.payload.PostResponse;
import com.masai.service.FileService;
import com.masai.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String  path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		List<PostDto>postDtos = postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto>postDtos = postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//get single post by postId
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postDto = postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> getAllPosts()
	{
		List<PostDto> postDtos = postService.getAllPosts();
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted successfully",true),HttpStatus.OK);
	}
	
	//update post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto updatedPostDto = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
	}
	
	//get all posts using pagination
	
	@GetMapping("/post/pagination")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
			)
	{
		PostResponse postResponse = postService.getAllPostsWithPagination(pageNumber, pageSize,sortBy, sortDir);
	
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	//search
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keywords)
	{
		List<PostDto>postDtos = postService.searchPosts(keywords);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//post image upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@PathVariable Integer postId,
			@RequestParam("image") MultipartFile image) throws IOException{
		
		PostDto postDto = postService.getPostById(postId);
		String fileName = fileService.uploadImage(path, image);
		
		postDto.setImageName(fileName);
		
		PostDto updatePost = postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable String imageName,
			HttpServletResponse response) throws IOException {
		
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
	}
	
}
