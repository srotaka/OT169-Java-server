package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Comment;
import com.alkemy.ong.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByNewsId(News idNews);
}
