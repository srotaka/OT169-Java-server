package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.service.impl.TestimonialServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
class TestimonialControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    TestimonialServiceImpl testimonialService;

    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    private final String URL = "/testimonials";

    TestimonialDto testimonialDto = new TestimonialDto ();

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                .apply(springSecurity())
                                .build();
    }

    @Test
    @DisplayName("Save Testimonial: Success (Code 201 Created) with role ADMIN")
    @WithMockUser(roles = "ADMIN")
    void saveTestimonialSuccess() throws Exception {
        testimonialDto.setName("Testimonial Name");
        testimonialDto.setImage("http://aws.com/img01.jpg");
        testimonialDto.setContent("New content text");

        when(testimonialService.save(testimonialDto)).thenReturn(testimonialDto);
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testimonialDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Testimonial Name"))
                .andExpect(jsonPath("$.image").value("http://aws.com/img01.jpg"))
                .andExpect(jsonPath("$.content").value("New content text"))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("Fail Saving Testimonial due to incorrect role (Error 403 Forbidden)")
    @WithMockUser(roles = "ADMIN")
    void saveTestimonialNotAdmin() throws Exception {
        testimonialDto.setName("Testimonial Name");
        testimonialDto.setImage("http://aws.com/img01.jpg");
        testimonialDto.setContent("New content text");

        when(testimonialService.save(testimonialDto)).thenReturn(testimonialDto);
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testimonialDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Fail Saving Testimonial due to incomplete fields (Error 400 Bad Request)")
    void saveTestimonialWithEmptyFields() throws Exception {
        testimonialDto.setName("");
        testimonialDto.setImage("");
        testimonialDto.setContent("New content text");

        when(testimonialService.save(testimonialDto)).thenReturn(testimonialDto);
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testimonialDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @DisplayName("Delete Testimonial: Success (Code 200 OK)")
    void deleteTestimonialSuccess() throws Exception {

        testimonialService.delete("abc123");
        mockMvc.perform(delete(URL+"/{id}", "abc123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Update Testimonials: Success (Code 200 OK)")
    void updateTestimonialsSuccess() throws Exception {
        testimonialDto.setId("abc123");
        testimonialDto.setName("Testimonial Name");
        testimonialDto.setImage("http://aws.com/img01.jpg");
        testimonialDto.setContent("New content text");

        when(testimonialService.updateTestimonials(testimonialDto, "abc123")).thenReturn(testimonialDto);
        mockMvc.perform(put(URL+"/{id}", "abc123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testimonialDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Testimonial Name"))
                .andExpect(jsonPath("$.image").value("http://aws.com/img01.jpg"))
                .andExpect(jsonPath("$.content").value("New content text"))
                .andDo(MockMvcResultHandlers.print());

    }
}