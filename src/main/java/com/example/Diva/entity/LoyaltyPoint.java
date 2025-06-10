package com.example.Diva.entity;

import jakarta.persistence.*;

@Entity
public class LoyaltyPoint extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Merchant merchant;

    @ManyToOne
    private User customer;

    private Integer points; // total

    private String reason; // optional: orderId or productId
}
