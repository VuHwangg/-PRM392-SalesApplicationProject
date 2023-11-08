package com.fpt.PRM392_FinalProject.controller;

import com.fpt.PRM392_FinalProject.dto.OrderDTOResponse;
import com.fpt.PRM392_FinalProject.dto.OrderDetailDTOResponse;
import com.fpt.PRM392_FinalProject.service.CartService;
import com.fpt.PRM392_FinalProject.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/order")
public class OrderController {
    OrderService orderService;

    @GetMapping("/{id}")
    public List<OrderDTOResponse> getOrderByUserId(@PathVariable int id) {
        return orderService.getOrderByUserId(id);
    }

    @GetMapping("/detail/{id}")
    public List<OrderDetailDTOResponse> getOrderDetailByOrderId(@PathVariable int id) {
        return orderService.getOrderDetailByOrderId(id);
    }
}
