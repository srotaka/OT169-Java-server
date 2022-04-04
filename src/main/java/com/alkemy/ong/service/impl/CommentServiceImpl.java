package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequestDto;
import com.alkemy.ong.dto.CommentResponseDto;
import com.alkemy.ong.entity.Comment;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentResponseDto> getAllComments() {
        return repository.findAll(Sort.by("timestamp").descending())
                         .stream()
                         .map(comment -> Mapper.mapToDto(comment, new CommentResponseDto()))
                         .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Void> addComment(CommentRequestDto commentRequestDto) {
        Optional<User> optionalUser = userRepository.findById(commentRequestDto.getUser_id());
        Optional<News> optionalNews = newsRepository.findById(commentRequestDto.getPost_id());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build(); //return 404 if the given user does not exist
        } else if (optionalNews.isEmpty()) {
            return ResponseEntity.notFound().build(); //return 404 if the given post does not exist
        }

        commentRepository.save(new Mapper().mapFromDto(commentRequestDto,new Comment(),optionalUser.get(),optionalNews.get()));

        return ResponseEntity.ok().build();
    }
}
