package com.fpt.PRM392_FinalProject.mapper;

import com.fpt.PRM392_FinalProject.dto.ProductDTODetailResponse;
import com.fpt.PRM392_FinalProject.dto.ProductDTOHomeResponse;
import com.fpt.PRM392_FinalProject.entity.Product;

public class ProductMapper {
    public static ProductDTOHomeResponse toProductDTOHomeResponse(Product product) {
        return ProductDTOHomeResponse.builder()
                .id(product.getId())
                .image(product.getImage())
                .name(product.getName())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .color(product.getColor())
                .build();
    }

    public static ProductDTODetailResponse toProductDTODetailResponse(Product product) {
        return ProductDTODetailResponse.builder()
                .id(product.getId())
                .image(product.getImage())
                .name(product.getName())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .description(product.getDescription())
                .supplier(product.getSupplier())
                .category(product.isCategory())
                .color(product.getColor())
                .build();
    }

}
