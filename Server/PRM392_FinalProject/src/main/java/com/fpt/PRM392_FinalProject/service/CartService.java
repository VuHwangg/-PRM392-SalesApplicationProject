package com.fpt.PRM392_FinalProject.service;

import com.fpt.PRM392_FinalProject.dto.CartDTOListResponse;
import com.fpt.PRM392_FinalProject.dto.CartDTOUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartService {
    List<CartDTOListResponse> getCartsByUserId(int id);

    ResponseEntity<?> updateCart(CartDTOUpdateRequest cartDTOUpdateRequest);
}
