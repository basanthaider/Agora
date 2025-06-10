package com.example.Diva.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Image> images;
}
