package com.masai.service;

import java.util.List;

import com.masai.entities.Post;
import com.masai.payload.PostDto;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	Post updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	List<Post> getAllPosts();
	
	//get single post
	Post getPostById(Integer postId);
	
	//get all posts by category
	List<Post> getAllPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<Post> getPostsByUser(Integer userId);
	
	//search posts
	List<Post> searchPosts(String keyword);
	
	
	

}
