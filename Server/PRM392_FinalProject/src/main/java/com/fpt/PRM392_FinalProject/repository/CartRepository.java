package com.fpt.PRM392_FinalProject.repository;

import com.fpt.PRM392_FinalProject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByCustomer_Id(int id);
}
