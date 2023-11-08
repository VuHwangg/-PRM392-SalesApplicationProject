package com.fpt.PRM392_FinalProject.repository;

import com.fpt.PRM392_FinalProject.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findAllByOrder_Id(int id);
}
