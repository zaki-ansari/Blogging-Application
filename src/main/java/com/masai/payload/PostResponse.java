package com.masai.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
	
	private List<PostDto> content;
	private Integer pageSize;
	private Integer pageNumber;
	private Long totalElements;
	private Integer totalPages;
	
	private boolean lastPage;
	
	

}
