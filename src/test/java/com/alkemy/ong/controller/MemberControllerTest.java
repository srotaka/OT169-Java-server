package com.alkemy.ong.controller;

import com.alkemy.ong.entity.Member;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
class MemberControllerTest {

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private MemberService service;
    @MockBean
    private MemberRepository repository;

    ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

    }
   //   ******** TEST CREATED MEMBERS ********
   //   **************************************
    /*Create Member*/
    /*Method return 200 Ok*/
    @Test
    @DisplayName("Create member, method should save new member and return 200 ok if the user has Role 'ADMIN'")
    void createMemberIfUserHasRoleAdmin()throws Exception{

        Member miembroFull;
        String id = String.valueOf(UUID.randomUUID());
        miembroFull = new Member();
        miembroFull.setId(id);
        miembroFull.setName("Prueba");
        miembroFull.setFacebookUrl("facebook.com");
        miembroFull.setInstagramUrl("instagram.com");
        miembroFull.setLinkedinUrl("linkedin.com");
        miembroFull.setImage("img.jpg");
        miembroFull.setDescription("Es una prueba");
        miembroFull.setTimestamp(Timestamp.from(Instant.now()));
        miembroFull.setSoftDelete(false);


        when(service.save(miembroFull)).thenReturn(miembroFull);

        mockMvc.perform(post("/members/")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(miembroFull))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

    /*Method return 400 Bad Request*/
    @Test
    @DisplayName("Create member, method should return 400 Bad Request if request is not valid'")
    void createMemberReturnBadRequest()throws Exception{



        when(service.save(null)).thenReturn(null);

        mockMvc.perform(post("/members/")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(null))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    /*Method return 401 Unauthorized*/
    @Test
    @DisplayName("Create member, method should return 401 Unauthorized if user not authenticated'")
    void createMemberReturnUnauthorized()throws Exception{



        Member miembroFull = new Member();
        String id = String.valueOf(UUID.randomUUID());
        miembroFull = new Member();
        miembroFull.setId(id);
        miembroFull.setName("Prueba");
        miembroFull.setFacebookUrl("facebook.com");
        miembroFull.setInstagramUrl("instagram.com");
        miembroFull.setLinkedinUrl("linkedin.com");
        miembroFull.setImage("img.jpg");
        miembroFull.setDescription("Es una prueba");
        miembroFull.setTimestamp(Timestamp.from(Instant.now()));
        miembroFull.setSoftDelete(false);

        when(service.save(miembroFull)).thenReturn(miembroFull);

        mockMvc.perform(post("/members/")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(miembroFull))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    /*Method return 403 Forbidden*/
    @Test
    @DisplayName("Create member, method return 403  if the user has Role 'USER'")
    void createMemberIfUserHasRoleUser()throws Exception{
        Member miembroFull = new Member();
        String id = String.valueOf(UUID.randomUUID());
        miembroFull = new Member();
        miembroFull.setId(id);
        miembroFull.setName("Prueba");
        miembroFull.setFacebookUrl("facebook.com");
        miembroFull.setInstagramUrl("instagram.com");
        miembroFull.setLinkedinUrl("linkedin.com");
        miembroFull.setImage("img.jpg");
        miembroFull.setDescription("Es una prueba");
        miembroFull.setTimestamp(Timestamp.from(Instant.now()));
        miembroFull.setSoftDelete(false);

        when(service.save(miembroFull)).thenReturn(miembroFull);

        mockMvc.perform(post("/members/")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(miembroFull))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }

    /*Update Members*/



}