package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentRequestDto;
import com.alkemy.ong.dto.CommentResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICommentService {
    List<CommentResponseDto> getAllComments();

    ResponseEntity<Void> addComment(CommentRequestDto commentRequestDto);

    public ResponseEntity<Void> delete(String id);

}
