package com.fpt.PRM392_FinalProject.service.impl;

import com.fpt.PRM392_FinalProject.dto.OrderDTOResponse;
import com.fpt.PRM392_FinalProject.dto.OrderDetailDTOResponse;
import com.fpt.PRM392_FinalProject.entity.Order;
import com.fpt.PRM392_FinalProject.exception.Exception;
import com.fpt.PRM392_FinalProject.mapper.OrderDetailMapper;
import com.fpt.PRM392_FinalProject.mapper.OrderMapper;
import com.fpt.PRM392_FinalProject.repository.OrderDetailRepository;
import com.fpt.PRM392_FinalProject.repository.OrderRepository;
import com.fpt.PRM392_FinalProject.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDTOResponse> getOrderByUserId(int id) {
        //TODO: checking if user exist
        return orderRepository.findAllByCustomer_Id(id)
                .stream()
                .map(OrderMapper::toOrderDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTOResponse> getOrderDetailByOrderId(int id) {
        return orderDetailRepository.findAllByOrder_Id(id).stream()
                .map(OrderDetailMapper::ToOrderDetailDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTOResponse changeOrderStatus(int id, int status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> Exception.badRequest("Order not found", "api/v1/order/{id}"));
        order.setStatus(status);
        orderRepository.save(order);

        return OrderMapper.toOrderDTOResponse(order);
    }
}
