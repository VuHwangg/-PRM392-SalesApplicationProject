package com.fpt.PRM392_FinalProject.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    double price;
    double discount;
    String supplier;
    boolean category;
    @Nationalized
    String description;
    String image;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    List<Cart> carts;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<OrderDetail> orderDetailList;
}
