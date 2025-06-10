package com.example.Diva.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Color extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String hexCode;
}
