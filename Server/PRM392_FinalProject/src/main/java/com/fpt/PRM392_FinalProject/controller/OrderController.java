package com.fpt.PRM392_FinalProject.controller;

import com.fpt.PRM392_FinalProject.dto.OrderDTOAddRequest;
import com.fpt.PRM392_FinalProject.dto.OrderDTOResponse;
import com.fpt.PRM392_FinalProject.dto.OrderDetailDTOResponse;
import com.fpt.PRM392_FinalProject.service.CartService;
import com.fpt.PRM392_FinalProject.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/order")
public class OrderController {
    private static final int STATUS_CANCEL = 4;
    OrderService orderService;

    @GetMapping("/{id}")
    public List<OrderDTOResponse> getOrderByUserId(@PathVariable int id) {
        return orderService.getOrderByUserId(id);
    }

    @GetMapping("/detail/{id}")
    public List<OrderDetailDTOResponse> getOrderDetailByOrderId(@PathVariable int id) {
        return orderService.getOrderDetailByOrderId(id);
    }

    @PutMapping("/{id}")
    public OrderDTOResponse changeOrderStatus(@PathVariable int id) {
        return orderService.changeOrderStatus(id, STATUS_CANCEL);
    }

    @PostMapping()
    public OrderDTOAddRequest addOrder(@RequestBody OrderDTOAddRequest orderDTOAddRequest){
        return orderService.addOrder(orderDTOAddRequest);
    }

}
