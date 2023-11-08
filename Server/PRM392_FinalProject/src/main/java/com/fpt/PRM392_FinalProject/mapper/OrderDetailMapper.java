package com.fpt.PRM392_FinalProject.mapper;

import com.fpt.PRM392_FinalProject.dto.OrderDetailDTOResponse;
import com.fpt.PRM392_FinalProject.entity.Order;
import com.fpt.PRM392_FinalProject.entity.OrderDetail;

public class OrderDetailMapper {
    public static OrderDetailDTOResponse ToOrderDetailDTOResponse(OrderDetail orderDetail) {
        return OrderDetailDTOResponse.builder()
                .id(orderDetail.getProduct().getId())
                .image(orderDetail.getProduct().getImage())
                .name(orderDetail.getProduct().getName())
                .price(orderDetail.getPrice())
                .quantity(orderDetail.getQuantity())
                .color(orderDetail.getProduct().getColor())
                .build();
    }
}
