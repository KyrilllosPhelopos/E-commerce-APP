package com.sawiris.ecommerce.service.brand;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sawiris.ecommerce.entity.Brand;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.repository.BrandRepo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BrandServiceImp  implements BrandService{
	private final BrandRepo brandRepo;
	
	public List<Brand> listAllBrands()
	{
		return brandRepo.findAll();
	}
	public Brand getBrandById(long id)
	{
		return brandRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand Not Found"));
	}
	public Brand getBrandByname(String name)
	{
		return brandRepo.getBrandByname(name);
	}
	
	public Brand addBrand(Brand brand)
	{
		return brandRepo.save(brand);
	}
	
	public Brand updateBrand(Brand brand)
	{
		return brandRepo.save(brand);
	}
	
	public void deleteBrand(long id)
	{
		brandRepo.deleteById(id);
	}


}
