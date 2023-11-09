package com.fpt.PRM392_FinalProject.controller;

import com.fpt.PRM392_FinalProject.dto.CartDTOAddRequest;
import com.fpt.PRM392_FinalProject.dto.CartDTOAddResponse;
import com.fpt.PRM392_FinalProject.dto.CartDTOListResponse;
import com.fpt.PRM392_FinalProject.dto.CartDTOUpdateRequest;
import com.fpt.PRM392_FinalProject.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/cart")
public class CartController {
    CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @GetMapping("/{id}")
    public List<CartDTOListResponse> getCartsByUserId(@PathVariable int id) {
        return cartService.getCartsByUserId(id);
    }

    @PostMapping
    public CartDTOUpdateRequest updateCart(@RequestBody CartDTOUpdateRequest cartDTOUpdateRequest) {
        cartDTOUpdateRequest.getPost_cart_dtos().forEach((post_cart_product_dto) ->{
            logger.info(post_cart_product_dto.toString());
        });
        return cartService.updateCart(cartDTOUpdateRequest);
    }

    @GetMapping("/quantity/{id}")
    public Integer getProductQuantityInCart(@PathVariable int id) {
        return cartService.getProductQuantityInCart(id);
    }

    @PostMapping("/add")
    public CartDTOAddResponse addToCart(@RequestBody CartDTOAddRequest cartDTOAddRequest) {
        return cartService.addToCart(cartDTOAddRequest);
    }
}
