package com.sawiris.ecommerce.service.brand;

import java.util.List;

import com.sawiris.ecommerce.entity.Brand;


public interface BrandService {
	
	public List<Brand> listAllBrands();
	public Brand getBrandById(long id);
	public Brand getBrandByname(String name);
	
	public Brand addBrand(Brand brand);
	
	public Brand updateBrand(Brand brand);
	
	public void deleteBrand(long id);
	
	
}
