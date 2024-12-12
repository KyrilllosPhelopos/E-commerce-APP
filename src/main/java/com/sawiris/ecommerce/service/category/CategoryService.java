package com.sawiris.ecommerce.service.category;


import org.springframework.data.domain.Page;

import com.sawiris.ecommerce.entity.Category;

public interface CategoryService {

	public Page<Category> listAllCategorys(int page, int size);
	public Category getCategoryById(long id);
	public Category getCategoryByname(String name);
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public void deleteCategory(Long id);
}
