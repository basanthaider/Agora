package com.example.Diva.repository;

import com.example.Diva.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    // Additional query methods can be defined here if needed
}
