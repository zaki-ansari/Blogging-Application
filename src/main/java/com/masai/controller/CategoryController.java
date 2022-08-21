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
import com.masai.payload.CategoryDto;
import com.masai.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	//post - create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategoryHandler(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory = categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//put - update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategoryHandler(@Valid @PathVariable("categoryId") Integer id,@RequestBody CategoryDto categoryDto)
	{
		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
		
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//delete - delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategoryHandler(@PathVariable Integer categoryId)
	{
		categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully",true),HttpStatus.OK);
	}
	
	//get - get_category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryHandler(@PathVariable Integer categoryId)
	{
		CategoryDto categoryDto = categoryService.getCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategoryHandler()
	{
		List<CategoryDto> categoryDtos = categoryService.getAllCategory();
		
		return new ResponseEntity<List<CategoryDto>>(categoryDtos,HttpStatus.OK);
	}
	
	
	
}
