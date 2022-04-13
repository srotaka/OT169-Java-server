package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.entity.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.impl.TestimonialServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
class TestimonialControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    TestimonialServiceImpl testimonialService;

    @MockBean
    TestimonialRepository testimonialRepository;
    @Autowired
    TestimonialController testimonialController;

    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    private final String URL = "/testimonials";
    private final static int PAGE = 0;
    private final static int SIZE = 2;
    private final static String PAGINATION_URL =  "/testimonials/pages?page=";

    TestimonialDto testimonialDto = new TestimonialDto();
    Testimonial testimonialEntity = new Testimonial();
    Testimonial testimonialEntity2 = new Testimonial();
    Testimonial testimonialEntity3 = new Testimonial();
    Testimonial testimonialEntity4 = new Testimonial();
    List<Testimonial> testimonialEntityList = new ArrayList<>();

    @BeforeEach
    public void setup(){

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                .apply(springSecurity())
                                .build();
        testimonialDto.setId("abc123");
        testimonialDto.setName("Testimonial Name");
        testimonialDto.setImage("http://aws.com/img01.jpg");
        testimonialDto.setContent("Some content text");

        testimonialEntity.setId("abc123");
        testimonialEntity.setName("Testimonial Name");
        testimonialEntity.setImage("http://aws.com/img01.jpg");
        testimonialEntity.setContent("Some content text 01");

        testimonialEntity2.setId("abc456");
        testimonialEntity2.setName("Testimonial Name");
        testimonialEntity2.setImage("http://aws.com/img02.jpg");
        testimonialEntity2.setContent("Some content text 02");

        testimonialEntity3.setId("abc789");
        testimonialEntity3.setName("Testimonial Name");
        testimonialEntity3.setImage("http://aws.com/img03.jpg");
        testimonialEntity3.setContent("Some content text 03");

        testimonialEntity4.setId("abc321");
        testimonialEntity4.setName("Testimonial Name");
        testimonialEntity4.setImage("http://aws.com/img04.jpg");
        testimonialEntity4.setContent("Some content text 04");

        testimonialEntityList.add(testimonialEntity);
        testimonialEntityList.add(testimonialEntity2);
        testimonialEntityList.add(testimonialEntity3);
        testimonialEntityList.add(testimonialEntity4);

    }
    /* ======================================
        TESTS FOR SAVING A NEW TESTIMONIAL
    =========================================*/
    @Test
    @DisplayName("Save Testimonial: Success (Code 201 Created) with role ADMIN")
    @WithMockUser(roles = "ADMIN")
    void saveTestimonial__Success() throws Exception {
        TestimonialDto testimonialToSave = new TestimonialDto();
        testimonialToSave.setId("def456");
        testimonialToSave.setName("New Testimonial Name");
        testimonialToSave.setImage("http://aws.com/img02.jpg");
        testimonialToSave.setContent("New content text");

        when(testimonialService.save(testimonialToSave)).thenReturn(testimonialToSave);
        mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(testimonialToSave))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Testimonial Name"))
                .andExpect(jsonPath("$.image").value("http://aws.com/img02.jpg"))
                .andExpect(jsonPath("$.content").value("New content text"));
    }
    @Test
    @DisplayName("Fail Saving Testimonial due to incorrect role (Error 403 Forbidden)")
    @WithMockUser(roles = "USER")
    void saveTestimonial__FailBecauseUserIsNotAdmin() throws Exception {
        TestimonialDto testimonialToSave = new TestimonialDto();
        testimonialToSave.setName("New Testimonial Name");
        testimonialToSave.setImage("http://aws.com/img02.jpg");
        testimonialToSave.setContent("New content text");

        when(testimonialService.save(testimonialToSave)).thenReturn(testimonialToSave);
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testimonialToSave))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Fail Saving Testimonial because user is not authenticated (Error 401 Unauthorized)")
    void saveTestimonial__FailBecauseUserIsAuthenticated() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Fail Saving Testimonial due to incomplete fields (Error 400 Bad Request)")
    @WithMockUser(roles = "ADMIN")
    void saveTestimonial__FailBecauseOfEmptyFields() throws Exception {

        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    /* ======================================
        TESTS FOR DELETING A TESTIMONIAL
    =========================================*/
    @Test
    @DisplayName("Delete Testimonial: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    void deleteTestimonial__Success() throws Exception {

        testimonialRepository.delete(testimonialEntity);
        verify(testimonialRepository, times(1)).delete(testimonialEntity);

        mockMvc.perform(delete(URL+"/abc123"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Fail Deleting Testimonial due to incorrect user role (Error 403 Forbidden)")
    @WithMockUser(roles = "USER")
    void deleteTestimonial__FailBecauseUserIsNotAdmin() throws Exception {
        testimonialService.delete("abc123");
        mockMvc.perform(delete(URL+"/abc123")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Fail Deleting Testimonial because user is not authenticated (Error 401 Unauthorized)")
    void deleteTestimonial__FailBecauseUserIsNotAuthenticated() throws Exception {
        testimonialService.delete("abc123");
        mockMvc.perform(delete(URL+"/abc123")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Fail Deleting Testimonial due to non-existent ID (Error 404 Not Found)")
    @WithMockUser(roles = "ADMIN")
    void deleteTestimonial__FailBecauseIdNotFound() throws Exception {
        String notExistingId = "xyz789";
        when(testimonialController.delete(notExistingId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(delete(URL+"/{id}",notExistingId)
                        .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isNotFound());
    }

    /* ======================================
        TESTS FOR UPDATING A TESTIMONIAL
    =========================================*/
    @Test
    @DisplayName("Update Testimonials: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    void updateTestimonials__Success() throws Exception {

        testimonialDto.setName("New Testimonial Name");
        testimonialDto.setImage("http://aws.com/new-img01.jpg");
        testimonialDto.setContent("New content text");

        when(testimonialService.updateTestimonials(testimonialDto, "abc123")).thenReturn(testimonialDto);
        mockMvc.perform(put(URL+"/abc123")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testimonialDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Testimonial Name"))
                .andExpect(jsonPath("$.image").value("http://aws.com/new-img01.jpg"))
                .andExpect(jsonPath("$.content").value("New content text"));
    }

    @Test
    @DisplayName("Fail Updating Testimonial due to incorrect role (Error 403 Forbidden)")
    @WithMockUser(roles = "USER")
    void updateTestimonial__FailBecauseUserIsNotAdmin() throws Exception {

        testimonialDto.setName("New Testimonial Name");
        testimonialDto.setImage("http://aws.com/new-img01.jpg");
        testimonialDto.setContent("New content text");

        when(testimonialService.save(testimonialDto)).thenReturn(testimonialDto);
        mockMvc.perform(put(URL+"/abc123")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testimonialDto))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Fail Updating Testimonial because user is not authenticated (Error 401 Unauthorized)")
    void updateTestimonial__FailBecauseUserIsNotAuthenticated() throws Exception {

        mockMvc.perform(put(URL+"/abc123")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Fail Updating Testimonial due to incomplete fields (Error 400 Bad Request)")
    @WithMockUser(roles = "ADMIN")
    void updateTestimonial__FailBecauseOfEmptyFields() throws Exception {
        testimonialDto.setName("");
        testimonialDto.setImage("");
        testimonialDto.setContent("New content text");

        mockMvc.perform(put(URL+"/abc123")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testimonialDto))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Fail Updating Testimonial due to non-existent ID (Error 404 Not Found)")
    @WithMockUser(roles = "ADMIN")
    void updateTestimonial__FailBecauseIdNotFound() throws Exception {
        testimonialDto.setName("New Testimonial Name");
        testimonialDto.setImage("http://aws.com/new-img01.jpg");
        testimonialDto.setContent("New content text");
        when(testimonialService.updateTestimonials(testimonialDto, "xyz789")).thenThrow(new RuntimeException());

        mockMvc.perform(put(URL+"/xyz789")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(testimonialDto)))
                .andExpect(status().isNotFound());
    }

    /* ======================================
        TESTS FOR GETTING TESTIMONIAL PAGES
    =========================================*/
    @Test
    @DisplayName("Get All Testimonials Pages: Success (Code 200 OK)")
    @WithMockUser(roles="USER")
    void getAllPage__Success() throws Exception {

        Map<String, Object> response = new LinkedHashMap<>();
        when(testimonialService.getAllPages(0)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get(PAGINATION_URL)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(response)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Fail Getting All Testimonials Pages (Code 500 Internal Server Error)")
    @WithMockUser(roles="USER")
    void getAllPage__FailInternalServerError() throws Exception {

        when(testimonialService.getAllPages(0)).thenThrow(new Exception("Fail to load pages"));

        mockMvc.perform(MockMvcRequestBuilders.get(PAGINATION_URL)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    @DisplayName("Fail Getting All Testimonials Pages because user is not authenticated (Error 401 Unauthorized)")
    void getAllPage__FailBecauseUserIsNotAuthenticated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(PAGINATION_URL)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}