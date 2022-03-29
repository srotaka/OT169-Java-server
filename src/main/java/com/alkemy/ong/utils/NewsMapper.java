package com.alkemy.ong.utils;


import com.alkemy.ong.dto.CategoryBasicDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.entity.News;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NewsMapper {


  public News newsDto2Entity(NewsDto dto, News news){

      news.setName(dto.getName());
      news.setContent(dto.getContent());
      news.setImage(dto.getImage());
      news.setCategories(dto.getCategories().stream()
                                            .map(categoryDto->Mapper.mapToEntity(categoryDto, new Category()))
                                            .collect(Collectors.toList()));

      return news;
  }

  public NewsDto newsEntity2Dto(News news, NewsDto dto){

      dto.setName(news.getName());
      dto.setContent(news.getContent());
      dto.setImage(news.getImage());
      dto.setCategories(news.getCategories().stream()
                                            .map(category -> Mapper.mapToDto(category, new CategoryBasicDto()))
                                            .collect(Collectors.toList()));
    return dto;
  }



}
