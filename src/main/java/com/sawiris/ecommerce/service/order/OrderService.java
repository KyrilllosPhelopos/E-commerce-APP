package com.sawiris.ecommerce.service.order;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sawiris.ecommerce.dto.OrderItemDTO;
import com.sawiris.ecommerce.entity.Order;
import com.sawiris.ecommerce.entity.OrderItem;

public interface OrderService {
	
	
	public Order createOrder(Long userId, List<OrderItemDTO> orderItemsDtos);
	public List<Order> getOrdersByUserId(Long userId);
	public Order getOrderById(Long orderId);
	public Order updateOrderStatus(Long orderId, com.sawiris.ecommerce.enums.OrderStatus status);
	public Order addOrderItems(Long orderId, List<OrderItemDTO> OrderItemDtos);
	public void deleteOrderItem(Long itemId);

}
