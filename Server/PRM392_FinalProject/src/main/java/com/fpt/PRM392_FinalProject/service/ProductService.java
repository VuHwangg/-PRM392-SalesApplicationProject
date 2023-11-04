package com.fpt.PRM392_FinalProject.service;

import com.fpt.PRM392_FinalProject.dto.ProductDTOHomeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<ProductDTOHomeResponse> getAllProduct();
}
