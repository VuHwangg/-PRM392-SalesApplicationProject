package com.fpt.PRM392_FinalProject.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    Customer customer;

    String phone;
    String address;
    LocalDate date;
    float total;
    int status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    List<OrderDetail> orderDetailList;

}
