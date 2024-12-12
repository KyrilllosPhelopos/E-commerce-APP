package com.sawiris.ecommerce.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sawiris.ecommerce.entity.Brand;
import com.sawiris.ecommerce.entity.Category;
import com.sawiris.ecommerce.entity.Product;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.repository.BrandRepo;
import com.sawiris.ecommerce.repository.CategoryRepo;
import com.sawiris.ecommerce.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
	private final ProductRepo productRepo;
	private final BrandRepo brandRepo;
	private final CategoryRepo categoryRepo;

	public Page<Product> listAllProducts(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return productRepo.findAll(pageable);
	}
	public Page<Product> listAllProducts(String search,int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return productRepo.findProductsByNameAndDescription(search, pageable) ;
	}

	public Product getProductById(long id)
	{
		return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand Not Found"));
	}

	public List<Product> addProduct(List<Product> products)
	{
		for (Product product : products) {
			// Check if the brand exists, if not create it
			product.setBrand(findOrCreateBrand(product.getBrand()));

			// Check if the category exists, if not create it
			product.setCategory(findOrCreateCategory(product.getCategory()));
		}

		return productRepo.saveAll(products);
	}

	@Transactional
	private Brand findOrCreateBrand(Brand brand) {
		if (brand == null || brand.getName() == null || brand.getName().isEmpty()) {
			return null; // Handle invalid case
		}
		Optional<Brand> existingBrand = Optional.ofNullable(brandRepo.getBrandByname(brand.getName()));
		
		if (existingBrand.isPresent()) {
	        return existingBrand.get();
	    }
	    return brandRepo.save(brand);
	}

	@Transactional
	private Category findOrCreateCategory(Category category) {
		if (category == null || category.getName() == null || category.getName().isEmpty()) {
			return null; // Handle invalid case
		}
		Optional<Category> existingCategory = Optional.ofNullable(categoryRepo.getCategoryByname(category.getName()));
		
		if (existingCategory.isPresent()) {
	        return existingCategory.get();
	    }
	    return categoryRepo.save(category);
	}



	public List<Product> updateProduct(List<Product> products)
	{
		return productRepo.saveAll(products);
	}

	public void deleteProduct(Long id)
	{
		productRepo.deleteById(id);
	}

}
