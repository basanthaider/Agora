package com.example.Diva.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Orders order;

    @ManyToOne
    private ProductVariant variant;

    private Integer quantity;
    private Double price;
}