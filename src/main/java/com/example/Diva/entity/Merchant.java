package com.example.Diva.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Merchant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "merchant")
    private List<Product> products;

    private String businessName;

    @OneToMany(mappedBy = "merchant")
    private List<LoyaltyPoint> loyaltyPoints;
}
