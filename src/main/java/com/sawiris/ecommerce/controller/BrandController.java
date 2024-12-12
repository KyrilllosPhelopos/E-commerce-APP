package com.sawiris.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sawiris.ecommerce.entity.Brand;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.service.brand.BrandService;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/brands")
public class BrandController {

	private final BrandService brandService;
	
	@GetMapping
	public List<Brand> getBrandList()
	{
		return brandService.listAllBrands();
	}
	
	
	@GetMapping("/{id}")
	public Brand getBrandById(@PathVariable Long id)
	{
		return brandService.getBrandById(id);
	}
	
	@PostMapping
	public Brand addBrand(@RequestBody Brand brand)
	{
	    System.out.println("Before saving: " + brand.getName());

	
		return brandService.addBrand(brand);
	}
	
	@PutMapping()
	public Brand updateBrand(@RequestBody Brand brand )
	{
		return brandService.updateBrand(brand);
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<String> deleteBrand(@PathVariable Long id)
	{
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
	}
	
	
	
	
	
	
}
