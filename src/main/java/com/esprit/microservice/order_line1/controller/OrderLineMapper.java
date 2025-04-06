package com.esprit.microservice.order_line1.controller;

import com.esprit.microservice.order.entity.Order;
import com.esprit.microservice.order_line1.entity.OrderLine;
import com.esprit.microservice.order_line1.repository.OrderLineRequest;
import com.esprit.microservice.order_line1.repository.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(Long.valueOf(request.orderId()))
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
