package com.fpt.PRM392_FinalProject.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTOResponse {
    int id;
    String customerName;
    String address;
    String phone;
    int status;
    double price;
}
