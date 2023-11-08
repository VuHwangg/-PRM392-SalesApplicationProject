package com.fpt.PRM392_FinalProject.repository;

import com.fpt.PRM392_FinalProject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByCustomer_Id(int id);

    Void deleteAllById (int id);

    Integer countAllByCustomer_Id(int id);
}
