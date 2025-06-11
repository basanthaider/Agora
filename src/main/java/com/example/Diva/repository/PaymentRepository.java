package com.example.Diva.repository;

import com.example.Diva.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Additional query methods can be defined here if needed
}
