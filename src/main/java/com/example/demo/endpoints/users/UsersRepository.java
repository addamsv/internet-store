package com.example.demo.endpoints.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

//    @Query("Select * from table_name where column_name  like %:keyword% ALLOW FILTERING ")
    // SELECT * FROM "users" WHERE "email" = ?;
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<Users> findUserByEmail(String email);
//    Users findOneByEmail(String email);

}
