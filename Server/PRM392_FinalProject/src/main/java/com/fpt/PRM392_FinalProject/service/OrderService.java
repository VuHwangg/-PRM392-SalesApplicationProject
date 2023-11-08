package com.fpt.PRM392_FinalProject.service;

import com.fpt.PRM392_FinalProject.dto.OrderDTOResponse;
import com.fpt.PRM392_FinalProject.dto.OrderDetailDTOResponse;
import com.fpt.PRM392_FinalProject.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDTOResponse> getOrderByUserId(int id);

    List<OrderDetailDTOResponse> getOrderDetailByOrderId(int id);

    OrderDTOResponse changeOrderStatus(int id, int status);
}
