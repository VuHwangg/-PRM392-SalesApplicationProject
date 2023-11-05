package com.fpt.PRM392_FinalProject.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOHomeResponse {
    int id;
    String image;
    String name;
    double price;
    double discount;
    private String color;
}
