package com.example.demo.endpoints.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // SELECT * FROM "brand" WHERE "name" = ?;
    @Query("SELECT r FROM Role r WHERE r.value = ?1")
    Optional<Role> findRoleByValue(String value);
}
