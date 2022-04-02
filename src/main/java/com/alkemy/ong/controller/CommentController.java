package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentResponseDto;
import com.alkemy.ong.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
