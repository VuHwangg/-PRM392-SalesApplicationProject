package com.fpt.PRM392_FinalProject.controller;

import com.fpt.PRM392_FinalProject.dto.CartDTOListResponse;
import com.fpt.PRM392_FinalProject.dto.CartDTOUpdateRequest;
import com.fpt.PRM392_FinalProject.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/cart")
public class CartController {
    CartService cartService;

    @GetMapping("/{id}")
    public List<CartDTOListResponse> getCartsByUserId(@PathVariable int id) {
        return cartService.getCartsByUserId(id);
    }

    @PostMapping
    public ResponseEntity<?> updateCart(@RequestBody CartDTOUpdateRequest cartDTOUpdateRequest) {
        return cartService.updateCart(cartDTOUpdateRequest);
    }
}
