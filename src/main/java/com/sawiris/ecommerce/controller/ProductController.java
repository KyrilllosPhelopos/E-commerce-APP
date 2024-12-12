package com.sawiris.ecommerce.controller;

import java.util.List;

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

import com.sawiris.ecommerce.entity.Product;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping
	public Page<Product> search(@RequestParam(defaultValue = "") String searchKeyword,
								@RequestParam(defaultValue = "0") int page,
								@RequestParam(defaultValue = "10") int size)
	{
		return productService.listAllProducts(searchKeyword, page, size);
	}
	
	@PostMapping
	public List<Product> addNewProducts(@RequestBody List<Product> products)
	{
		return productService.addProduct(products);
	}
	
	@PutMapping
	public List<Product> updateProducts(@RequestBody List<Product> products)
	{
		return productService.updateProduct(products);
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<String> deleteBrand(@PathVariable Long id)
	{
        try {
        	productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
	}
	
	

}
