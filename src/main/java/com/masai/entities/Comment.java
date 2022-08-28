package com.masai.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commentId;
	
	private String content;
	
	@ManyToOne
	private Post post;
	

}
