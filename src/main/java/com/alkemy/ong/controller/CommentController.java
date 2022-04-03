package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentResponseDto;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import com.alkemy.ong.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl service;
    @Autowired
    private NewsServiceImpl newsService;

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return service.getAllComments();
    }

    @GetMapping("/{idNews}")
    public ResponseEntity<?> getAllCommentsNews(String idNews){
        try{
            return ResponseEntity.status(OK).body(newsService.);
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
}
