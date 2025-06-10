package com.example.Diva.entity;

import com.example.Diva.utill.BaseEnums;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private BaseEnums.OrderStatus status;

    private LocalDate estimatedDelivery;

    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
}