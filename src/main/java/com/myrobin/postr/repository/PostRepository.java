package com.myrobin.postr.repository;

import com.myrobin.postr.table.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    @Query("SELECT p.username FROM Post p ORDER BY p.createdAtUtc0 DESC LIMIT :limit")
    List<String> findAllUsers(String limit);
}
