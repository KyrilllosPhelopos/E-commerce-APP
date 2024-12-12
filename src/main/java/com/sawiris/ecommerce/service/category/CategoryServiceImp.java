package com.sawiris.ecommerce.service.category;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sawiris.ecommerce.entity.Category;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.repository.CategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

	private final CategoryRepo categoryRepo;
	
	public Page<Category> listAllCategorys(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return categoryRepo.findAll(pageable);
	}
	public Category getCategoryById(long id)
	{
		return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand Not Found"));
	}
	public Category getCategoryByname(String name)
	{
		return categoryRepo.getCategoryByname(name);
	}
	
	public Category addCategory(Category category)
	{
		return categoryRepo.save(category);
	}
	
	public Category updateCategory(Category category)
	{
		return categoryRepo.save(category);
	}
	
	public void deleteCategory(Long id)
	{
	
		categoryRepo.deleteById(id);
	}
}
