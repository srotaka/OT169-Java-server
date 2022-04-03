package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.utils.Mapper;
import com.alkemy.ong.utils.NewsMapper;
import com.amazonaws.services.simplesystemsmanagement.model.ParameterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public NewsDto getNewsById(String id) {

        Optional<News> response = newsRepository.findById(id);
        if (response.isPresent()) {
            News news = response.get();
            return newsMapper.newsEntity2Dto(news, new NewsDto());
        } else {
            throw new ParameterNotFoundException("");
        }

    }

    @Override
    public void delete(String id) {
        newsRepository.deleteById(id);
    }


    @Override
    public NewsDto updateNews(String id, NewsDto newsDto) {

        Optional<News> optional = newsRepository.findById(id);

        if (optional.isPresent()) {
            News updatedNews = newsMapper.updateValues(newsRepository.findById(id).get(), newsDto);
            newsRepository.save(updatedNews);
                return newsDto;
        } else {
            throw new ParameterNotFoundException("");

        }
    }

    @Override
    public News getOne(String id) throws Exception {
        Optional<News> entity = newsRepository.findById(id);
        if(!entity.isPresent()){
            throw new Exception("Id news not found");
        }
        return entity.get();
    }
}
