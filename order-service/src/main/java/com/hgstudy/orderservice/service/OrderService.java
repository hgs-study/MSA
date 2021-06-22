package com.hgstudy.orderservice.service;


import com.hgstudy.orderservice.dto.OrderDto;
import com.hgstudy.orderservice.jpa.OrderEntity;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);


}
