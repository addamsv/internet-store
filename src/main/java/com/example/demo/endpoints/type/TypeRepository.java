package com.example.demo.endpoints.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{

    // SELECT * FROM "type" WHERE "name" = ?;
    @Query("SELECT t FROM Type t WHERE t.name = ?1")
    Optional<Type> findTypeByName(String name);
}
