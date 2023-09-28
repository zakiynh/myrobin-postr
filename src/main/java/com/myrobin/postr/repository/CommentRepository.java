package com.myrobin.postr.repository;

import com.myrobin.postr.table.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String > {

}
