package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentRequestDto;
import com.alkemy.ong.dto.CommentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICommentService {
    List<CommentResponseDto> getAllComments();

    ResponseEntity<Void> addComment(CommentRequestDto commentRequestDto);

    ResponseEntity<Void> putComment(@PathVariable String id, @RequestBody CommentRequestDto commentRequestDto);

    List<CommentResponseDto> getAllCommetsNews(String idNews)throws Exception;

    public ResponseEntity<Void> delete(String id);


}
