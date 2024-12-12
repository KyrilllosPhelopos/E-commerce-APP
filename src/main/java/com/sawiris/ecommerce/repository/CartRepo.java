package com.sawiris.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawiris.ecommerce.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {

}
