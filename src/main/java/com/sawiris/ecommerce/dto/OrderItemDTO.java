package com.sawiris.ecommerce.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDTO {

	long productId;
	BigDecimal price;
	int qty;
}
