package com.example.demo.endpoints.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // SELECT * FROM "device" WHERE "name" = ?;
    @Query("SELECT d FROM Device d WHERE d.name = ?1")
    Optional<Post> findPostByName(String name);

    // SELECT * FROM "device" WHERE "id" = ?; //INCLUDE [{ model: PostInfo, as: 'deviceInfo' }]
    @Query("SELECT d FROM Post d WHERE d.id = ?1")
    Optional<Post> findByIdPlusPostInfo(Long id);

    @Query("SELECT d FROM Post d")
    Optional<List<Post>> findAndCountAll(Integer limit, Integer offset);
}
