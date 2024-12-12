package com.sawiris.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawiris.ecommerce.entity.Order;
import com.sawiris.ecommerce.entity.User;

public interface OrderRepo extends JpaRepository<Order, Long> {
	
	List<Order> findOrderByUser(User user );

}
