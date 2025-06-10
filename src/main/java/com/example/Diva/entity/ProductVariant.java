package com.example.Diva.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductVariant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Color color;

    @ManyToOne
    private Size size;

    private Double price;
    private Integer stock;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<Image> images;
}
