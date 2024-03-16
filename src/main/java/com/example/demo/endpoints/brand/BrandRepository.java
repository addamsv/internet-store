package com.example.demo.endpoints.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    // SELECT * FROM "brand" WHERE "name" = ?;
    @Query("SELECT b FROM Brand b WHERE b.name = ?1")
    Optional<Brand> findBrandByName(String name);
}
