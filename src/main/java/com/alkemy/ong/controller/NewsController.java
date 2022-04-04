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
            newsService.getNewsById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews(@PathVariable String id,@Valid @RequestBody NewsDto newsDto) throws ResponseStatusException {
        try{
            newsService.updateNews( id, newsDto);
            return ResponseEntity.status(HttpStatus.OK).body(newsDto);
          }
            catch (Exception e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable String id) {
       newsService.delete(id);
       return ResponseEntity.status(HttpStatus.OK).build();
    }








    }




