package com.sawiris.ecommerce.service.product;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sawiris.ecommerce.entity.Product;

public interface ProductService {

	public Page<Product> listAllProducts(int page, int size);
	public Page<Product> listAllProducts(String search,int page, int size);

	public Product getProductById(long id);

	public List<Product> addProduct(List<Product> products);
	
	public List<Product> updateProduct(List<Product> products);
	
	public void deleteProduct(Long id);
}
