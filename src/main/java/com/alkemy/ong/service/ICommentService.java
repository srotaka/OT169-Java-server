package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentResponseDto;

import java.util.List;

public interface ICommentService {
    List<CommentResponseDto> getAllComments();
}
