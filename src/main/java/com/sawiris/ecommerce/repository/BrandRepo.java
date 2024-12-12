package com.sawiris.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawiris.ecommerce.entity.Brand;

public interface BrandRepo  extends JpaRepository<Brand, Long> {

	public Brand getBrandByname(String name);

}
