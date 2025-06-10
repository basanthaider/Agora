package com.example.Diva.entity;

import jakarta.persistence.*;

@Entity
public class Rating extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer stars; // 1 to 5
    private String review;
}
