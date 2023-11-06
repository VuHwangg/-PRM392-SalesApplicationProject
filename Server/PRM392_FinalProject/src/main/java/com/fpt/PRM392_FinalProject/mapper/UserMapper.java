package com.fpt.PRM392_FinalProject.mapper;

import com.fpt.PRM392_FinalProject.dto.UserDTOLoginResponse;
import com.fpt.PRM392_FinalProject.entity.Customer;

public class UserMapper {
    public static UserDTOLoginResponse toUserDTOLoginResponse(Customer customer) {
        return UserDTOLoginResponse.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .name(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .build();
    }


}
