package com.sawiris.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sawiris.ecommerce.dto.OrderItemDTO;
import com.sawiris.ecommerce.entity.Order;
import com.sawiris.ecommerce.request.OrderRequest;
import com.sawiris.ecommerce.request.OrderStatusUpdateRequest;
import com.sawiris.ecommerce.service.order.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	   @PostMapping
	    public Order createOrder(@RequestBody OrderRequest request) {
	        return orderService.createOrder(request.getUserId(), request.getOrderItemsDtos());
	    }
	   
	   @GetMapping("/users/{userId}")
	    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
	        return orderService.getOrdersByUserId(userId);
	    }
	    @GetMapping("/{orderId}")
	    public Order getOrderById(@PathVariable Long orderId) {
	        return orderService.getOrderById(orderId);
	    }
	    @PatchMapping("/{orderId}")
	    public Order updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatusUpdateRequest request) {
	        return orderService.updateOrderStatus(orderId, request.getStatus());
	    }
	    
	    @PostMapping("/{orderId}/items")
	    public Order addOrderItems(@PathVariable Long orderId, @RequestBody List<OrderItemDTO> orderItems) {
	        return orderService.addOrderItems(orderId, orderItems);
	    }
	    
	    @DeleteMapping("/items/{itemId}")
	    public void deleteOrderItem(@PathVariable Long itemId) {
	        orderService.deleteOrderItem(itemId);
	    }
	    
}
