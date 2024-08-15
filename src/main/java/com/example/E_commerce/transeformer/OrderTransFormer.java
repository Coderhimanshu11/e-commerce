package com.example.E_commerce.transeformer;

import com.example.E_commerce.dto.responseDto.OrderedResponseDto;
import com.example.E_commerce.model.Ordered;
import jakarta.persistence.criteria.Order;

public class OrderTransFormer {
    public static OrderedResponseDto orderToOrderResponseDto(Ordered order){
        return OrderedResponseDto.builder()
                .orderDate(order.getOrderDate())
                .CardUsed(order.getCardUsed())
                .customerName(order.getCustomer().getName())
                .orderNo(order.getOrderNo())
                .totalValue(order.getTotalValue())
                .build();
    }
}
