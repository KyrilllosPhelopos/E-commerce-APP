package com.sawiris.ecommerce.request;

import java.util.List;

import com.sawiris.ecommerce.dto.OrderItemDTO;
import com.sawiris.ecommerce.entity.Order;
import com.sawiris.ecommerce.entity.Product;

import lombok.Data;

@Data
public class OrderRequest {

	private Long userId;
	
	Product Product;	
	Order order;
	
    private List<OrderItemDTO> orderItemsDtos;

}
