package com.fpt.PRM392_FinalProject.repository;

import com.fpt.PRM392_FinalProject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
