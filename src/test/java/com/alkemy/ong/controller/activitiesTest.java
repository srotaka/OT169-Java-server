package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.impl.ActivityServiceImpl;
import com.alkemy.ong.utils.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class activitiesTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private ActivityServiceImpl service;
    @MockBean
    private ActivityRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();
    private final ActivityDto activityDtoToSave = new ActivityDto();
    private final Activity activityInTheDb = new Activity();
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(springSecurity())
                                 .build();

        activityDtoToSave.setName("New Activity");
        activityDtoToSave.setContent("Activity Content");
        activityDtoToSave.setImage("image.jpg");

        activityInTheDb.setId(String.valueOf(UUID.randomUUID()));
        activityInTheDb.setName("My Activity");
        activityInTheDb.setContent("Activity Content");
        activityInTheDb.setImage("img.jpg");
        activityInTheDb.setTimestamp(Timestamp.from(Instant.now()));
        activityInTheDb.setSoftDelete(false);

    }

    @Test
    @DisplayName("Add activity method should save new activity and return 200 ok if the user has Role 'ADMIN'")
    void addActivity__ShouldSaveActivityAndReturnOkIfUserHasRoleAdmin() throws Exception {

        when(service.addActivity(activityDtoToSave)).thenReturn(activityDtoToSave);

        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Add activity method should return 403 Forbidden if the user does not have Role 'ADMIN'")
    void addActivity__ShouldReturnForbiddenIfUserHasRoleUser() throws Exception {

        when(service.addActivity(activityDtoToSave)).thenReturn(activityDtoToSave);
        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                        .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Add activity method should return 401 Unauthorized if the user is not Authenticated")
    void addActivity__ShouldReturnUnauthorizedIfUserIsNotAuthenticated() throws Exception {

        when(service.addActivity(activityDtoToSave)).thenReturn(activityDtoToSave);
        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Add activity method should return 400 Bad Request if request is not valid")
    void addActivity__ShouldReturnBadRequestIfRequestIsNotValid() throws Exception {

        activityDtoToSave.setName("");
        when(service.addActivity(activityDtoToSave)).thenReturn(activityDtoToSave);
        mockMvc.perform(post("/activities")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Update activity method should update activity and return 200 ok if the user has Role 'ADMIN'")
    public void updateActivity__ShouldUpdateActivityAndReturnOkIfUserHasRoleAdmin() throws Exception {

        String id = activityInTheDb.getId();
        String url = String.format("/activities/%s", id);

        when(repository.findById(id)).thenReturn(Optional.of(activityInTheDb));
        when(service.updateActivity(id, activityDtoToSave)).thenReturn(Mapper.mapToDto(activityInTheDb, activityDtoToSave));

        mockMvc.perform(put(url)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(user("admin").roles("ADMIN")))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isNotEmpty())
                        .andExpect(jsonPath("$.id").value(id))
                        .andExpect((jsonPath("$.name").value(activityDtoToSave.getName())))
                        .andExpect((jsonPath("$.name").value(activityInTheDb.getName())));
    }

    @Test
    @DisplayName("Update activity method should return 404 Not Found if the Id does not exists.")
    public void updateActivity__ShouldReturnNotFoundIfIdIsNotExistant() throws Exception {

        String url = String.format("/activities/%s", "1");

        when(service.updateActivity("1", activityDtoToSave)).thenThrow(new RuntimeException());

        mockMvc.perform(put(url)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(user("admin").roles("ADMIN")))
                        .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Update activity method should return 400 Bad Request if the Request Body is invalid.")
    public void updateActivity__ShouldReturnBadRequestIfRequestIsNotValid() throws Exception {

        String id = activityInTheDb.getId();
        String url = String.format("/activities/%s", id);
        activityDtoToSave.setName("");

        when(service.updateActivity(id, activityDtoToSave)).thenReturn(activityDtoToSave);

        mockMvc.perform(put(url)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Update activity method should return 403 Forbidden if the user has Role 'USER'")
    public void updateActivity__ShouldReturnForbiddenIfUserHasRoleUser() throws Exception {

        String id = activityInTheDb.getId();
        String url = String.format("/activities/%s", id);


        when(service.updateActivity(id, activityDtoToSave)).thenReturn(activityDtoToSave);

        mockMvc.perform(put(url)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(activityDtoToSave))
                        .with(user("user").roles("USER")))
                        .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Update activity method should return 401 Unauthorized if the user is not authenticated")
    public void updateActivity__ShouldReturnForbiddenIfUserIsNotAuthenticated() throws Exception {

//        String id = String.valueOf(UUID.randomUUID());
//        String url = String.format("/activities/%s", id);
//        ActivityDto dto = new ActivityDto();
//        dto.setName("Updated Activity Name");
//        dto.setContent("Activity content");
//        dto.setImage("img.jpg");
//
//        when(service.updateActivity(id, dto)).thenReturn(dto);
//
//        mockMvc.perform(put(url)
//                        .contentType(APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(dto)))
//                        .andExpect(status().isUnauthorized());
    }

}