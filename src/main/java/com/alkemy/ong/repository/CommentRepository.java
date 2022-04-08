package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    @Query(value="SELECT * FROM comments WHERE news_id = :idNews;", nativeQuery = true)
    List<Comment> findCommentsByNewsId(String idNews);
}
