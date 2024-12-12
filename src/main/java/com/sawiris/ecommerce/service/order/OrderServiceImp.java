
package com.sawiris.ecommerce.service.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.sawiris.ecommerce.dto.OrderItemDTO;
import com.sawiris.ecommerce.entity.Order;
import com.sawiris.ecommerce.entity.OrderItem;
import com.sawiris.ecommerce.entity.Product;
import com.sawiris.ecommerce.entity.User;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.repository.OrderItemRepo;
import com.sawiris.ecommerce.repository.OrderRepo;
import com.sawiris.ecommerce.repository.ProductRepo;
import com.sawiris.ecommerce.repository.UserRepo;
import com.sawiris.ecommerce.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{
	private final OrderRepo orderRepo;
	private final OrderItemRepo orderItemRepo;
	private final UserRepo userRepo;
	private final ProductRepo productRepo;

	public Order createOrder(Long userId, List<OrderItemDTO> orderItemsDtos)
	{
		Order order = new Order ();
		Optional<User> user = userRepo.findById(userId);
		order.setUser(user.get());
		order.setStatus(com.sawiris.ecommerce.enums.OrderStatus.PENDING);
		
		order.setTotalPrice(
				orderItemsDtos.stream()
			              .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQty())))
			              .reduce(BigDecimal.ZERO, BigDecimal::add)
			);
		
		for(OrderItemDTO item : orderItemsDtos)
		{
			System.out.println("getting the prodiuct with id: " + item.getProductId());
			Product p = productRepo.findById(item.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product Not Found")); 
			System.out.println("successfully got the prodiuct with id: " + item.getProductId());

			OrderItem orderItem = new OrderItem ();
			orderItem.setProduct(p);
			orderItem.setPrice(item.getPrice());
			orderItem.setQty(item.getQty());
			orderItemRepo.save(orderItem);
			order.getItems().add(orderItem);
			
		}
        orderRepo.save(order);

        return order;
	
		
		
	}
	public List<Order> getOrdersByUserId(Long userId)
	{
		Optional<User> user = userRepo.findById(userId);
		List<Order> orders = orderRepo.findOrderByUser(user.get());
		return orders;
	}
	public Order getOrderById(Long orderId)
	{
		return orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order Not Founded"));
	}
	public Order updateOrderStatus(Long orderId, com.sawiris.ecommerce.enums.OrderStatus status)
	{
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Found"));
		
		order.setStatus(status);
		orderRepo.save(order);
		return order;
	}
	public Order addOrderItems(Long orderId, List<OrderItemDTO> orderItemsDtos)
	{
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Found"));
		
		BigDecimal additionalPrice = orderItemsDtos.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(order.getTotalPrice().add(additionalPrice));
        
        
    	for(OrderItemDTO item : orderItemsDtos)
		{
			Product p = productRepo.findById(item.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product Not Found")); 
			OrderItem orderItem = new OrderItem ();
			orderItem.setProduct(p);
			orderItem.setPrice(item.getPrice());
			orderItem.setQty(item.getQty());
			orderItemRepo.save(orderItem);
			order.getItems().add(orderItem);
			
		}
        orderRepo.save(order);
		return order;
		
	}
	public void deleteOrderItem(Long itemId)
	{
		orderItemRepo.deleteById(itemId);
	}
}
