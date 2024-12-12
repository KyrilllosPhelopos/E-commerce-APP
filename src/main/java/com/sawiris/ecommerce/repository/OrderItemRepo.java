package com.sawiris.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sawiris.ecommerce.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
