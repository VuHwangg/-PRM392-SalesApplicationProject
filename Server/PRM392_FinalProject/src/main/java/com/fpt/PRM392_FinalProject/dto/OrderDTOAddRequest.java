package com.fpt.PRM392_FinalProject.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTOAddRequest {
    int customerID;
    String customerPhone;
    String customerAddress;
    LocalDate orderDate;
    double totalPrice;
    List<OrderDetailDTOAddRequest> products;
}
