package com.fpt.PRM392_FinalProject.service.impl;

import com.fpt.PRM392_FinalProject.dto.CartDTOListResponse;
import com.fpt.PRM392_FinalProject.mapper.CartMapper;
import com.fpt.PRM392_FinalProject.repository.CartRepository;
import com.fpt.PRM392_FinalProject.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;

    @Override
    public List<CartDTOListResponse> getCartsByUserId(int id) {
        return cartRepository.findAllByCustomer_Id(id).stream()
                .map(CartMapper::toCartDTOListResponse)
                .collect(Collectors.toList());
    }
}
