package com.fpt.PRM392_FinalProject.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true, nullable = false)
    String username;
    String password;
    String name;
    String address;
    String phone;

    @OneToMany(mappedBy = "customer")
    List<Cart> carts;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    List<Order> orders;
}
