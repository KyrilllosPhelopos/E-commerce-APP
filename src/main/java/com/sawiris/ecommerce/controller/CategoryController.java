package com.sawiris.ecommerce.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.sawiris.ecommerce.entity.Category;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorys")
public class CategoryController {
	
private final CategoryService categoryService;
	
	@GetMapping
	public Page<Category> getCategoryList(@RequestParam(defaultValue = "0") int page,
										  @RequestParam(defaultValue = "10") int size)
	{
		return categoryService.listAllCategorys(page,size);
	}
	
	
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Long id)
	{
		return categoryService.getCategoryById(id);
	}
	
	@PostMapping
	public Category addCategory(@RequestBody Category category)
	{
	
		return categoryService.addCategory(category);
	}
	
	@PutMapping()
	public Category updateCatgeory(@RequestBody Category category )
	{
		return categoryService.updateCategory(category);
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<String> deleteCategory(@PathVariable Long id)
	{
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
	}

}
