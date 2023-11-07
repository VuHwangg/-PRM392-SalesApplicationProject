package com.fpt.PRM392_FinalProject.service;

import com.fpt.PRM392_FinalProject.dto.CartDTOListResponse;

import java.util.List;

public interface CartService {
    List<CartDTOListResponse> getCartsByUserId(int id);
}
