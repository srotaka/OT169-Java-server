package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryBasicDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.service.impl.NewsServiceImpl;
import com.alkemy.ong.utils.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import static java.util.Arrays.asList;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest{

    private final String URL = "/news";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private NewsRepository newsRepository;

    @MockBean
    private NewsService newsService;

    @Autowired
    ObjectMapper objectMapper;

    private News news;
    private NewsDto newsDto;
    private List<CategoryBasicDto>categories = new ArrayList<CategoryBasicDto>();



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        news = new News();
        news.setId("123abc");
        news.setName("name");
        news.setImage("image");
        news.setContent("content");
        news.setSoftDelete(false);


        newsDto = new NewsDto();
        newsDto.setName("name");
        newsDto.setImage("image");
        newsDto.setContent("content");
        categories.add(new CategoryBasicDto("name"));
        newsDto.setCategories(categories);

    }
    //POST//

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNews_Ok() throws Exception {
        when(newsService.save(newsDto)).thenReturn(newsDto);
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username = "ADMIN" , roles = {"ADMIN"})
    void createNews_BadRequest() throws Exception{

        newsDto.setName(null);
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto)))
                .andExpect(status().isBadRequest());


    }
    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void createNews_FailBecauseUserIsNotAdmin() throws Exception {
        when(newsService.save(newsDto)).thenReturn(newsDto);
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }

    //GET//

    @Test
    @WithMockUser(username = "ADMIN" , roles = {"ADMIN"})
    void getNewsById_Ok() throws Exception {
        String id = "123abc";
        when(newsService.getNewsById("123abc")).thenReturn(newsDto);

        mockMvc.perform(get("/news/{id}", "123abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "USER" , roles = {"USER"})
    void getAllPage_Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/news/pages".concat("?page=")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void getAllPage_Forbidden()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/news/pages".concat("?page=")))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    //PUT//

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void updateNews_Ok() throws Exception {
        String id = "123abc";

        when(newsService.updateNews("123abc", newsDto)).thenReturn(newsDto);
        mockMvc.perform(put(URL+"/123abc")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.image").value("image"))
                .andExpect(jsonPath("$.content").value("content"));
    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void updateNews_NotFoundById() throws Exception{
        String idNotFound = "456def";
        when(newsService.updateNews(idNotFound, newsDto)).thenThrow((new RuntimeException()));
        mockMvc.perform(put(URL+idNotFound)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto)))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void updateNews_FailBecauseUserIsNotAdmin() throws Exception{
        when(newsService.updateNews("123abc", newsDto)).thenReturn(newsDto);
        mockMvc.perform(put(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void updateNews_BadRequest() throws Exception{
        newsDto.setName(null);
        newsDto.setImage(null);
        mockMvc.perform(put(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());

    }



    //DELETE//

    @Test
    @WithMockUser(username = "ADMIN" , roles = {"ADMIN"})
    void delete_Ok() throws Exception{
        newsService.delete("123abc");

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/123abc")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void delete_NotFound() throws Exception{
        String idNotFound = "456def";
        newsService.delete("456def");

        mockMvc.perform(MockMvcRequestBuilders.delete(URL +idNotFound))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void delete_FailBecauseUserIsNotAdmin() throws Exception {
        newsService.delete("123abc");

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }

}