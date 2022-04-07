package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentRequestDto;
import com.alkemy.ong.dto.CommentResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICommentService {
    List<CommentResponseDto> getAllComments();

    ResponseEntity<Void> addComment(CommentRequestDto commentRequestDto);

    ResponseEntity<Void> delete(String id);

    ResponseEntity<Void> putComment(String id,CommentRequestDto commentRequestDto);

    List<CommentResponseDto> getAllCommetsNews(String idNews)throws Exception;

}
