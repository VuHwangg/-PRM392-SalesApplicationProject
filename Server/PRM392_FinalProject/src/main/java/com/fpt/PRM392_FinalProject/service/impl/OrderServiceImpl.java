package com.fpt.PRM392_FinalProject.service.impl;

import com.fpt.PRM392_FinalProject.dto.OrderDTOAddRequest;
import com.fpt.PRM392_FinalProject.dto.OrderDTOResponse;
import com.fpt.PRM392_FinalProject.dto.OrderDetailDTOResponse;
import com.fpt.PRM392_FinalProject.entity.Cart;
import com.fpt.PRM392_FinalProject.entity.Order;
import com.fpt.PRM392_FinalProject.entity.OrderDetail;
import com.fpt.PRM392_FinalProject.entity.Product;
import com.fpt.PRM392_FinalProject.exception.Exception;
import com.fpt.PRM392_FinalProject.mapper.OrderDetailMapper;
import com.fpt.PRM392_FinalProject.mapper.OrderMapper;
import com.fpt.PRM392_FinalProject.repository.CartRepository;
import com.fpt.PRM392_FinalProject.repository.OrderDetailRepository;
import com.fpt.PRM392_FinalProject.repository.OrderRepository;
import com.fpt.PRM392_FinalProject.repository.ProductRepository;
import com.fpt.PRM392_FinalProject.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;
    ProductRepository productRepository;
    CartRepository cartRepository;

    static final int STATUS_ORDERED = 1;

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

    @Override
    public OrderDTOAddRequest addOrder(OrderDTOAddRequest orderDTOAddRequest) {
        //TODO: checking user exits

        // Create new order
        Order order = OrderMapper.toOrder(orderDTOAddRequest);
        order.setStatus(STATUS_ORDERED);
        order = orderRepository.save(order);


        // Create order detail add to new order
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Order finalOrder = order;
        orderDTOAddRequest.getProducts().forEach((orderDetailDTOAddRequest) -> {

            //checking product exits
            Product product = productRepository
                    .findById(orderDetailDTOAddRequest.getId())
                    .orElseThrow(() -> Exception.badRequest("Product not exits", "api/v1/order"));
            //Create and save order detail
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(Order.builder()
                            .id(finalOrder.getId())
                            .build())
                    .price(product.getPrice() * orderDetailDTOAddRequest.getQuantity())
                    .quantity(orderDetailDTOAddRequest.getQuantity())
                    .product(Product.builder().id(product.getId()).build())
                    .build();
            orderDetailRepository.save(orderDetail);

            // Delete cart exits
            Cart cart = cartRepository.findByCustomer_IdAndProduct_Id(orderDTOAddRequest.getCustomerID(), product.getId());
            cartRepository.deleteById(cart.getId());
        });
        // Return
        return orderDTOAddRequest;
    }
}
