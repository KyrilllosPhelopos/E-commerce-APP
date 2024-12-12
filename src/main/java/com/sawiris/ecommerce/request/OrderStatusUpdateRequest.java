package com.sawiris.ecommerce.request;

import com.sawiris.ecommerce.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderStatusUpdateRequest {
    private OrderStatus status;

}
