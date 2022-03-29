package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsRepository newsRepository;

    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto news){

        NewsDto savedNews= newsService.save(news);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>delete(@PathVariable String id) {
        if (newsRepository.existsById(id)) {
            this.newsService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



}
