package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;


    @PostMapping
    public ResponseEntity<NewsDto> save(@Valid @RequestBody NewsDto news){
        NewsDto savedNews= newsService.save(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable String id) throws Exception {
        try{
            NewsDto newsDto= newsService.getNewsById(id);
            return ResponseEntity.status(HttpStatus.OK).body(newsDto);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews(@PathVariable String id,@Valid @RequestBody NewsDto newsDto) {
        newsService.updateNews( id, newsDto);
        return ResponseEntity.status(HttpStatus.OK).body(newsDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable String id) {
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPage(@RequestParam(defaultValue = "0") Integer page){
        Map<String, Object> news = new HashMap<>();
        try {
            news = newsService.getAllPages(page);
            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

