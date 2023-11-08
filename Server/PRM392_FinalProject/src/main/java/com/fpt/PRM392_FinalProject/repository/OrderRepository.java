package com.fpt.PRM392_FinalProject.repository;

import com.fpt.PRM392_FinalProject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomer_Id(int id);
}
