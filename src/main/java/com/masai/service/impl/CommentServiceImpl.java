package com.masai.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entities.Comment;
import com.masai.entities.Post;
import com.masai.exception.ResourceNotFoundException;
import com.masai.payload.CommentDto;
import com.masai.repository.CommentRepo;
import com.masai.repository.PostRepo;
import com.masai.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ","PostId ",postId));
		
		Comment comment = modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = commentRepo.save(comment);
		
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment " , "commentId ", commentId));
		
		commentRepo.delete(comment);
	}

}
