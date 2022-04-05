package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentRequestDto;
import com.alkemy.ong.dto.CommentResponseDto;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl service;

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return service.getAllComments();
    }

    @PostMapping()
    public ResponseEntity<Void> addComment(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        return service.addComment(commentRequestDto);
    }

    @GetMapping("/{idNews}")
    public ResponseEntity<?> getAllCommentsNews(String idNews) {
        try {
            return ResponseEntity.status(OK).body(service.getAllCommetsNews(idNews));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }
}