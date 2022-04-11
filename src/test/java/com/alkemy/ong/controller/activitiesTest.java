package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.impl.ActivityServiceImpl;
import com.alkemy.ong.utils.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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

    ActivityDto activityDto;
    Activity activity;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        activityDto = new ActivityDto();
        activityDto.setName("New Activity");
        activityDto.setContent("Activity Content");
        activityDto.setImage("image.jpg");

        activity = new Activity();
        activity.setId(String.valueOf(UUID.randomUUID()));
        activity.setName("My Activity");
        activity.setContent("Activity Content");
        activity.setImage("img.jpg");
        activity.setTimestamp(Timestamp.from(Instant.now()));
        activity.setSoftDelete(false);
    }

    @Test
    @DisplayName("Add activity method should save new activity and return 200 ok if the user has Role 'ADMIN'")
    @WithMockUser(roles = "ADMIN")
    void addActivity__ShouldSaveActivityAndReturnOkIfUserHasRoleAdmin() throws Exception {

        when(service.addActivity(activityDto)).thenReturn(activityDto);

        mockMvc.perform(post("/activities").with(csrf())
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Add activity method should return 400 Bad Request if request is not valid")
    @WithMockUser(roles = "ADMIN")
    void addActivity__ShouldReturnBadRequestIfRequestIsNotValid() throws Exception {

        activityDto.setName("");

        when(service.addActivity(activityDto)).thenReturn(activityDto);

        mockMvc.perform(post("/activities").with(csrf())
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Add activity method should return 401 Unauthorized if the user is not Authenticated")
    void addActivity__ShouldReturnUnauthorizedIfUserIsNotAuthenticated() throws Exception {

        when(service.addActivity(activityDto)).thenReturn(activityDto);

        mockMvc.perform(post("/activities").with(csrf())
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Add activity method should return 403 Forbidden if the user has Role 'USER'")
    @WithMockUser(roles = "USER")
    void addActivity__ShouldReturnForbiddenIfUserHasRoleUser() throws Exception {

        when(service.addActivity(activityDto)).thenReturn(activityDto);

        mockMvc.perform(post("/activities").with(csrf())
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Update activity method should update activity and return 200 ok if the user has Role 'ADMIN'")
    @WithMockUser(roles = "ADMIN")
    public void updateActivity__ShouldUpdateActivityAndReturnOkIfUserHasRoleAdmin() throws Exception {

        String id = activity.getId();
        String url = String.format("/activities/%s", id);

        when(repository.findById(id)).thenReturn(Optional.of(activity));
        when(service.updateActivity(id, activityDto)).thenReturn(Mapper.mapToDto(activity, activityDto));

        mockMvc.perform(put(url)
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect((jsonPath("$.name").value(activityDto.getName())))
                .andExpect((jsonPath("$.name").value(activity.getName())));
    }

    @Test
    @DisplayName("Update activity method should return 400 Bad Request if the Request Body is invalid.")
    @WithMockUser(roles = "ADMIN")
    public void updateActivity__ShouldReturnBadRequestIfRequestIsNotValid() throws Exception {

        String id = activity.getId();
        String url = String.format("/activities/%s", id);
        activityDto.setName("");

        when(service.updateActivity(id, activityDto)).thenReturn(activityDto);

        mockMvc.perform(put(url)
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Update activity method should return 401 Unauthorized if the user is not authenticated")
    public void updateActivity__ShouldReturnForbiddenIfUserIsNotAuthenticated() throws Exception {

        String id = String.valueOf(UUID.randomUUID());
        String url = String.format("/activities/%s", id);

        when(service.updateActivity(id, activityDto)).thenReturn(activityDto);

        mockMvc.perform(put(url)
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Update activity method should return 403 Forbidden if the user has Role 'USER'")
    @WithMockUser(roles = "USER")
    public void updateActivity__ShouldReturnForbiddenIfUserHasRoleUser() throws Exception {

        String id = activity.getId();
        String url = String.format("/activities/%s", id);

        when(service.updateActivity(id, activityDto)).thenReturn(activityDto);

        mockMvc.perform(put(url)
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Update activity method should return 404 Not Found if the Id does not exists.")
    @WithMockUser(roles = "ADMIN")
    public void updateActivity__ShouldReturnNotFoundIfIdIsNotExistant() throws Exception {

        String url = String.format("/activities/%s", "1");

        when(service.updateActivity("1", activityDto)).thenThrow(new RuntimeException());

        mockMvc.perform(put(url)
                        .content(asJson(activityDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    public static String asJson(Object object) {

        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }


}