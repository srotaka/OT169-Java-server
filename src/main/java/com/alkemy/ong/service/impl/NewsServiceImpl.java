package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.utils.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsRepository newsRepository;


    @Override
    public NewsDto save(NewsDto newsDto) {
        News newsEntity = newsMapper.newsDto2Entity(newsDto, new News());
        News saved = newsRepository.save(newsEntity);
        NewsDto result = newsMapper.newsEntity2Dto(saved, new NewsDto());

        return result;
    }

    @Override
    public void delete(String id) {
        newsRepository.deleteById(id);
    }
}
