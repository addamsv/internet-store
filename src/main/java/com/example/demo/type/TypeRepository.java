package com.example.demo.type;

import com.example.demo.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{

    // SELECT * FROM "users" WHERE "email" = ?;
//    @Query("SELECT u FROM Type u WHERE u.email = ?1")
//    Optional<Users> findUserByEmail(String email);
}
