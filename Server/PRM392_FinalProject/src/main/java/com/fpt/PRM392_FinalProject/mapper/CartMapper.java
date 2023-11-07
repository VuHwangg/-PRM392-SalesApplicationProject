package com.fpt.PRM392_FinalProject.mapper;

import com.fpt.PRM392_FinalProject.dto.CartDTOListResponse;
import com.fpt.PRM392_FinalProject.entity.Cart;

public class CartMapper {
    public static CartDTOListResponse toCartDTOListResponse(Cart cart) {
        return CartDTOListResponse.builder()
                .id(cart.getId())
                .image(cart.getProduct().getImage())
                .name(cart.getProduct().getName())
                .price(cart.getProduct().getPrice())
                .quantity(cart.getQuantity())
                .color(cart.getProduct().getColor())
                .build();
    }
}
