package com.alkemy.ong.service;


import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.News;

public  interface  NewsService {

     NewsDto save(NewsDto newsDto);

    void delete(String id);

    NewsDto getNewsById(String id) throws Exception;

    NewsDto updateNews(String id, NewsDto newsDto);

    News getOne(String id) throws Exception;
}
