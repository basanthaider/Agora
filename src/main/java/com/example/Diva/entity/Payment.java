package com.example.Diva.entity;
import jakarta.persistence.*;


@Entity
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String provider; // Stripe, ApplePay
    private String transactionId;
    private boolean paid;
}
