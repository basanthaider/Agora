package com.example.Diva.entity;

import jakarta.persistence.*;

@Entity
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressLine1;
    private String addressLine2;

    @ManyToOne
    private User user;
}
