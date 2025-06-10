package com.example.Diva.entity;

import jakarta.persistence.*;

@Entity
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private ProductVariant variant;

    private Integer quantity;
}
