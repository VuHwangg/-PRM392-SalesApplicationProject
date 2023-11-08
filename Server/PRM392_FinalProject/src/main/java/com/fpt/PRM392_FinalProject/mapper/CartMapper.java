package com.fpt.PRM392_FinalProject.mapper;

import com.fpt.PRM392_FinalProject.dto.CartDTOAddResponse;
import com.fpt.PRM392_FinalProject.dto.CartDTOListResponse;
import com.fpt.PRM392_FinalProject.dto.CartDTOUpdateRequest;
import com.fpt.PRM392_FinalProject.entity.Cart;

public class CartMapper {
    public static CartDTOListResponse toCartDTOListResponse(Cart cart) {
        return CartDTOListResponse.builder()
                .id(cart.getProduct().getId())
                .image(cart.getProduct().getImage())
                .name(cart.getProduct().getName())
                .price(cart.getProduct().getPrice())
                .quantity(cart.getQuantity())
                .color(cart.getProduct().getColor())
                .build();
    }

    public static CartDTOAddResponse toCartDTOAddResponse(Cart cart) {
        return CartDTOAddResponse.builder()
                .cartId(cart.getId())
                .quantity(cart.getQuantity())
                .customerId(cart.getCustomer().getId())
                .productId(cart.getProduct().getId())
                .build();
    }
}
