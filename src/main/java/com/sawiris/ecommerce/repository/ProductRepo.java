package com.sawiris.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sawiris.ecommerce.entity.Product;

public interface ProductRepo   extends JpaRepository<Product, Long>{
	
	 @Query(value = "SELECT * FROM product p WHERE p.name LIKE %:search% OR p.description LIKE %:search%", nativeQuery = true)
	 Page<Product> findProductsByNameAndDescription(@Param("search") String search, Pageable pageable);

}
