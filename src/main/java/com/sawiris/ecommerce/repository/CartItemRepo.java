package com.sawiris.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawiris.ecommerce.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem,Long>  {

}
