package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findCommentsByNewsId(String idNews);
}
