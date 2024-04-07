package com.example.demo.endpoints.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // SELECT * FROM "brand" WHERE "name" = ?;
    @Query("SELECT r FROM Role r WHERE r.value = ?1")
    Optional<Role> findRoleByValue(String value);

//    @Query(value = "SELECT r FROM Roles r INNER JOIN UsersRoles ur ON r.id = ur.roleId WHERE ur.users_id = ?1 ")
//    @Query(value = "select r.* from roles r inner join users_roles ur on r.id = ur.users_id", nativeQuery = true)
//    List<Role> findAllByUserId(long userId);
}
