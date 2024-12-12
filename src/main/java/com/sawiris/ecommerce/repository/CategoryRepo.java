package com.sawiris.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawiris.ecommerce.entity.Category;


public interface CategoryRepo   extends JpaRepository<Category, Long>{
	
	public Category getCategoryByname(String name);


}
