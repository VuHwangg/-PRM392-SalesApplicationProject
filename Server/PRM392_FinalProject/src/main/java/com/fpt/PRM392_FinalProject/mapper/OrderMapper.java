package com.fpt.PRM392_FinalProject.mapper;

import com.fpt.PRM392_FinalProject.dto.OrderDTOAddRequest;
import com.fpt.PRM392_FinalProject.dto.OrderDTOResponse;
import com.fpt.PRM392_FinalProject.entity.Customer;
import com.fpt.PRM392_FinalProject.entity.Order;

public class OrderMapper {
    public static OrderDTOResponse toOrderDTOResponse(Order order) {
        return OrderDTOResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomer().getName())
                .address(order.getAddress())
                .phone(order.getPhone())
                .status(order.getStatus())
                .price(order.getTotal())
                .build();
    }

    public static Order toOrder(OrderDTOAddRequest orderDTOAddRequest) {
        return Order.builder()
                .customer(Customer.builder()
                        .id(orderDTOAddRequest.getCustomerID())
                        .build()
                )
                .phone(orderDTOAddRequest.getCustomerPhone())
                .address(orderDTOAddRequest.getCustomerAddress())
                .date(orderDTOAddRequest.getOrderDate())
                .total(orderDTOAddRequest.getTotalPrice())
                .build();
    }
}
