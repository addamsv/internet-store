package com.example.demo.endpoints.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.zipCode = ?1 AND a.street = ?2 AND a.houseNumber = ?3")
    Optional<Address> findByAddress(String zipCode, String street, String houseNumber);
}
