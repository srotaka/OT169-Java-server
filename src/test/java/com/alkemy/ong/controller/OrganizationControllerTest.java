package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationRequestDto;
import com.alkemy.ong.dto.OrganizationResponseDto;

import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.entity.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.service.impl.OrganizationServiceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import com.alkemy.ong.service.impl.TestimonialServiceImpl;
import com.alkemy.ong.utils.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

class OrganizationControllerTest {

    @Autowired
    private WebApplicationContext context;
    @MockBean
    OrganizationServiceImpl organizationService;
    @MockBean
    OrganizationRepository organizationRepository;
    @MockBean
    SlideService slideService;
    @MockBean
    SlideController slideController;
    @MockBean
    TestimonialServiceImpl testimonialService;
    @MockBean
    TestimonialController testimonialController;
    @MockBean
    Mapper mapper;

    @Autowired
    OrganizationController organizationController;

    private MockMvc mockMvc;

    Organization organization = new Organization();
    OrganizationRequestDto organizationRequestDto = new OrganizationRequestDto();
    OrganizationResponseDto organizationResponseDto = new OrganizationResponseDto();
    SlideResponseDto slide = new SlideResponseDto();
    List<SlideResponseDto> slideList = new ArrayList<>();

    private final String URL = "/organization";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        organization.setId("1");
        organization.setName("Somos Más");
        organization.setImage("image01.jpg");
        organization.setEmail("somos@mas.com");
        organization.setWelcomeText("Bienvenidos");

        Map<String, String> contactInfo = new HashMap<>();
        contactInfo.put("Facebook", "facebook/somosmas.com");

        organizationResponseDto.setName("Somos Más");
        organizationResponseDto.setImage("image01.jpg");
        organizationResponseDto.setPhone(123L);
        organizationResponseDto.setAddress("Moreno 456");
        organizationResponseDto.setContact(contactInfo);

        organizationRequestDto.setName("Somos Más");
        organizationRequestDto.setImage("image01.jpg");
        organizationRequestDto.setPhone(123L);
        organizationRequestDto.setAddress("Moreno 456");
        organizationRequestDto.setEmail("somos@mas.com");
        organizationRequestDto.setWelcomeText("Bienvenidos");
        organizationRequestDto.setAboutUsText("Somos una organización sin fines de lucro.");
        organizationRequestDto.setFacebookUrl("facebook/somosmas.com");
        organizationRequestDto.setInstagramUrl("instagram/somosmas.com");
        organizationRequestDto.setLinkedinUrl("linkedin/somosmas.com");

        slide.setOrder(1);
        slide.setOrg("1");
        slide.setImgUrl("slide.jpg");
        slide.setText("Slide text");

        slideList.add(slide);

    }
    /* =================================
        TESTS FOR GET ORGANIZAZATION
    ====================================*/

    @Test
    @DisplayName("Get Ong: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    void getOng__Success() throws Exception {

        when(organizationService.getPublicInfo()).thenReturn(organizationResponseDto);

        mockMvc.perform(get(URL+"/public")
                .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk());

    }
    @Test
    @DisplayName("Get Ong: Success (Code 401 UNAUTHORIZED)")
    void getOng__ShouldReturnUnauthorized() throws Exception {

        when(organizationService.getPublicInfo()).thenReturn(organizationResponseDto);

        mockMvc.perform(get(URL+"/public")
                .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isUnauthorized());

    }
    @Test
    @DisplayName("Get Ong: Success (Code 201 NO CONTENT)")
    @WithMockUser(roles = "ADMIN")
    void getOng__ShouldReturnNoContent() throws Exception {

        List<OrganizationResponseDto> noItemsList = new ArrayList<>();
        when(organizationController.getPublicInfo()).thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));

        mockMvc.perform(get(URL+"/public")
                .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isNoContent());

    }


    /* =================================
        TESTS FOR POST ORGANIZAZATION
    ====================================*/

    @Test
    @DisplayName("Add organization: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    void addOng__ShouldSaveOngAndReturnOkIfUserHasRoleAdmin() throws Exception {

        when(organizationService.postPublicInfo(organizationRequestDto)).thenReturn(organizationResponseDto);

        mockMvc.perform(post(URL+"/public").with(csrf())
                .content(asJson(organizationRequestDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Add organization: Success (Code 401 UNAUTHORIZED)")
    void addOng__ShouldSaveOngAndReturnUnauthorized() throws Exception {

        when(organizationService.postPublicInfo(organizationRequestDto)).thenReturn(organizationResponseDto);

        mockMvc.perform(post(URL+"/public").with(csrf())
                .content(asJson(organizationRequestDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
    @Test
    @DisplayName("Add organization: Success (Code 400 BAD REQUEST )")
    @WithMockUser(roles = "ADMIN")
    void addOng__ShouldSaveOngAndReturnBadRequest() throws Exception {

        OrganizationRequestDto request = new OrganizationRequestDto();

        OrganizationResponseDto response = new OrganizationResponseDto();

        when(organizationService.postPublicInfo(request)).thenReturn(response);

        mockMvc.perform(post(URL+"/public").with(csrf())
                .content(asJson(response)).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    /* ==========================================
        TESTS FOR GETTING ORGANIZAZATION SLIDES
    =============================================*/

    @DisplayName("Get All Slides of organization: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    @Test
    void getAllSlides__Success() throws Exception {

        when(slideService.slideForOng("1")).thenReturn(slideList);

        mockMvc.perform(get(URL+"/public/Slides/1")
                .contentType(APPLICATION_JSON)
                .content(asJson(slideList))
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @DisplayName("Get All Slides of organization: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    @Test
    void getAllSlides__NotFound() throws Exception {

        when(slideService.slideForOng("2")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get(URL+"/public/Slides/")
                .contentType(APPLICATION_JSON)
                .with(csrf()))
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
