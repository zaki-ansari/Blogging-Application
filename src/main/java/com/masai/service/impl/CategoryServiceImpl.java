package com.masai.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entities.Category;
import com.masai.exception.ResourceNotFoundException;
import com.masai.payload.CategoryDto;
import com.masai.repository.CategoryRepo;
import com.masai.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," id ",categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat = categoryRepo.save(cat);
		return modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," id ",categoryId));
		
		categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," id ",categoryId));
		return modelMapper.map(cat , CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category>categories = categoryRepo.findAll();
		List<CategoryDto>catDtos = categories.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
