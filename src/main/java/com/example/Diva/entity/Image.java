package com.example.Diva.entity;

import jakarta.persistence.*;

@Entity
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @ManyToOne
    private ProductVariant variant;
    @ManyToOne
    private Brand brand;
}
