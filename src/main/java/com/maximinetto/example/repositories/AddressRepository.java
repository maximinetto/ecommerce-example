package com.maximinetto.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maximinetto.example.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
  
}
