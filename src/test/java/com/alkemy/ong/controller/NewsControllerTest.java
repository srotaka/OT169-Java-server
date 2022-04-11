package com.alkemy.ong.controller;

import com.alkemy.ong.entity.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.impl.NewsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest{

        @Autowired
        protected MockMvc mockMvc;

        @Autowired
        private NewsRepository newsRepository;

        @Mock
        private NewsServiceImpl newsServiceImpl;

         @Autowired
         ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();


    }


    @Test
    @WithMockUser(username = "ADMIN" , roles = {"ADMIN"})
    void createNews_Ok() throws Exception {

      News news = new News();
        news.setId("01");
        news.setImage("soy una imagen");
        news.setName("nombre");
        news.setContent("content");
        //VER QUE HAGO CON EL CATEGORIES
        news = newsRepository.save(news);

         //   when(newsRepository.save(news)).thenReturn(news);

       mockMvc.perform(post("/news")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
        //RESULTADO:  OK
    }

    @Test
    @WithMockUser(username = "ADMIN" , roles = {"ADMIN"})
    void createNews_BadRequest() throws Exception{
        News news = new News();
        news.setId("01"); //SETEO EL ID???????
        news.setImage(null);
        news.setName(null);
        news.setContent(null);
        //news = newsRepository.save(news);


        mockMvc.perform(post("/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(news)))
                .andExpect(status().isBadRequest());
        // RESULTADO: ME DICE QUE ESPERA UN 400 PERO DEVUELVE UN 403.
    }



    @Test
    void getNewsById() {

    }

    @Test
    void updateNews() {
    }

    @Test
    @WithMockUser(username = "ADMIN" , roles = {"ADMIN"})
    void delete_Ok() throws Exception{
        News news = new News();
        news.setId("123abc");

         //when(newsRepository.findById(news.getId())).thenReturn(Optional.of(news));

        mockMvc.perform(MockMvcRequestBuilders.delete("/news/{id}",20))
               .andExpect(status().isOk());

            /*RESULTADO: espera un 200 devuelve un 404*/

             /*News news = new News();
              newsRepository.delete(news);
                 mockMvc.perform(delete(URL+"/" + news.getId()))
                         .andExpect(status().isOk());*/
    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    void delete_NotFound() throws Exception{
        News news = new News();
        news.setId("123abc");

        mockMvc.perform(MockMvcRequestBuilders.delete("/news/{id}", news.getId()))
                .andExpect(status().isNotFound());

            //resultado: ok ???????????????
    }

}