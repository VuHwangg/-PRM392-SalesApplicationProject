package com.fpt.PRM392_FinalProject.service.impl;

import com.fpt.PRM392_FinalProject.dto.*;
import com.fpt.PRM392_FinalProject.entity.Cart;
import com.fpt.PRM392_FinalProject.entity.Customer;
import com.fpt.PRM392_FinalProject.entity.Product;
import com.fpt.PRM392_FinalProject.exception.Exception;
import com.fpt.PRM392_FinalProject.mapper.CartMapper;
import com.fpt.PRM392_FinalProject.repository.CartRepository;
import com.fpt.PRM392_FinalProject.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;

    @Override
    public List<CartDTOListResponse> getCartsByUserId(int id) {
        return cartRepository.findAllByCustomer_Id(id).stream()
                .map(CartMapper::toCartDTOListResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CartDTOUpdateRequest updateCart(CartDTOUpdateRequest cartDTOUpdateRequest) {
        //TODO: Checking the customer id is exits in database and productId

        List<Cart> cartList = cartRepository.findAllByCustomer_Id(cartDTOUpdateRequest.getCusID());
        for (Cart c: cartList) {
            cartRepository.deleteById(c.getId());
        }
        for (POST_Cart_Product_DTO post_cart: cartDTOUpdateRequest.getPost_cart_dtos()) {
            Cart cart = Cart.builder()
                    .product(Product.builder()
                            .id(post_cart.getId())
                            .build())
                    .customer(Customer.builder()
                            .id(cartDTOUpdateRequest.getCusID())
                            .build())
                    .quantity(post_cart.getQuantity())
                    .build();
            cartRepository.save(cart);
        }
        return cartDTOUpdateRequest;
    }

    @Override
    public Integer getProductQuantityInCart(int id) {
        return cartRepository.countAllByCustomer_Id(id);
    }

    @Override
    public CartDTOAddResponse addToCart(CartDTOAddRequest cartDTOAddRequest) {
        //TODO: Checking the customer id is exits in database and productId
        Cart cart = cartRepository.
                findByCustomer_IdAndProduct_Id(cartDTOAddRequest.getCustomerId(), cartDTOAddRequest.getProductId());
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + 1);

        } else {
            cart = Cart.builder()
                    .quantity(1)
                    .customer(Customer.builder()
                            .id(cartDTOAddRequest.getCustomerId())
                            .build()
                    )
                    .product(Product.builder()
                            .id(cartDTOAddRequest.getProductId())
                            .build()
                    )
                    .build();
        }
        Cart cartAdded = cartRepository.save(cart);
        return CartMapper.toCartDTOAddResponse(cartAdded);
    }


}
