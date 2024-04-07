package com.example.demo.endpoints.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

    // SELECT * FROM "users" WHERE "email" = ?;
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<Users> findUserByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.activationLink = ?1")
    Optional<Users> findByActivationLink(String link);

    @Query("SELECT u FROM Users u WHERE u.refreshToken = ?1")
    Optional<Users> findByRefreshToken(String refreshToken);
}
