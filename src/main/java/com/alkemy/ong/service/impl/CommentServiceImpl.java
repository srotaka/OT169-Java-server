package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentResponseDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.Comment;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.ICommentService;
import com.alkemy.ong.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository repository;
    @Autowired
    private NewsServiceImpl newsService;
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
    public List<CommentResponseDto> getAllCommetsNews(String idNews) throws Exception {
        News entity = newsService.getOne(idNews); //Verificar que el id existe
        if(entity == null){
            throw new Exception("Id not found");
        }
        List<Comment> entities = commentRepository.findAll(); // traer todos los comments
        List<Comment> fil = new ArrayList<>();
        for(Comment comment : entities){
            if(comment.getNews_id().getId().equals(idNews))
            fil.add(comment); // agregar los comentarios pertenecientes al idNews
        }
        List<CommentResponseDto> dtos = new ArrayList<>();
        for (Comment comment : fil){
            dtos.add(Mapper.mapToDto(comment,new CommentResponseDto())); // convertir la entidad a dto
        }
            return dtos;
    }

}
