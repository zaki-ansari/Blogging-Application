package com.masai.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min=4,message = "Min size of category title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,message = "Min size of category description is 4")
	private String categoryDescription;
	
	

}
