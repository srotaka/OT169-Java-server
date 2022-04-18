package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.entity.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.impl.ContactServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


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
class ContactControllerTest {

    private static String ROOT_PATH = "http://localhost:8080/contacts";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    ContactController contactController;
    @MockBean
    ContactServiceImpl contactService;
    @MockBean
    ContactRepository contactRepository;
    ObjectMapper mapper = new ObjectMapper();

    Contact contact;
    ContactDto contactDto;
    List<Contact> listContact = new ArrayList<>();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        contactDto = new ContactDto();
        contactDto.setId("1234");
        contactDto.setName("Dario");
        contactDto.setPhone("155253702");
        contactDto.setEmail("dario@mail.com");
        contactDto.setMessage("Hola");

        contact = new Contact();
        contact.setId("1111");
        contact.setName("Josue");
        contact.setPhone("155253732");
        contact.setEmail("josue@mail.com");
        contact.setMessage("welcome");
        contact.setTimestamp(Timestamp.from(Instant.now()));
        contact.setSoftDelete(false);

        listContact.add(contact);

    }

    /* =============================
        TESTS FOR POST CONTACT
    ================================*/
    @Test
    @DisplayName("Add contact method should save new contact and return 201 CREATED if the user has Role 'ADMIN'")
    @WithMockUser(roles = "ADMIN")
    void addContact__ShouldSaveContactAndReturnOkIfUserHasRoleAdmin() throws Exception {

        //ContactDto c2 = new ContactDto();
        when(contactService.create(contactDto)).thenReturn(contactDto);

        mockMvc.perform(post(ROOT_PATH).with(csrf())
                .content(asJson(contactDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("Add contact method should return 401 Unauthorized if the user is not authenticated")
    void addContact__ShouldReturnForbiddenIfUserIsNotAuthenticated() throws Exception {

        when(contactService.create(contactDto)).thenReturn(contactDto);

        mockMvc.perform(post(ROOT_PATH).with(csrf())
                .content(asJson(contactDto)).contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @DisplayName("Add contact method should return 400 Bad Request if request is not valid")
    @WithMockUser(roles = "ADMIN")
    void addContact__ShouldReturnBadRequest() throws Exception {

        ContactDto c1 = new ContactDto();
        c1.setName("");
        c1.setEmail("");

        when(contactService.create(c1)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        mockMvc.perform(post(ROOT_PATH).with(csrf())
                .content(asJson(c1)).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    /* ======================================
        TESTS FOR GETTING ALL CONTACTS
    =========================================*/
    @Test
    @DisplayName("Get All Contacts: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    void getAllContacts__Success() throws Exception {

        when(contactRepository.findAll()).thenReturn(listContact);
        mockMvc.perform(get(ROOT_PATH)
                .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk());

    }
    @Test
    @DisplayName("Fail Getting All contacts because user is not authenticated (Error 401 Unauthorized)")
    void getAllContacts__ShouldReturnUserIsNotAuthenticated() throws Exception {

        when(contactRepository.findAll()).thenReturn(listContact);
        mockMvc.perform(get(ROOT_PATH)
                .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isUnauthorized());

    }
    @Test
    @DisplayName("Fail Getting All Contacts due to incorrect role (Error 403 Forbidden)")
    @WithMockUser(roles = "USER")
    void getAllContacts__ShouldReturnForbiddenIfUserHasRoleUser() throws Exception {

        when(contactRepository.findAll()).thenReturn(listContact);
        mockMvc.perform(get(ROOT_PATH)
                .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isForbidden());

    }

    @DisplayName("Fail getting All Users because List is empty (Error 204 No Content)")
    @WithMockUser(roles = "ADMIN")
    @Test
    void getAllContacts__FailBecauseListIsEmpty() throws Exception {
        List<ContactDto> noItemsList = new ArrayList<>();
        when(contactController.getContactList()).thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));

        mockMvc.perform(get(ROOT_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(noItemsList))
                .with(csrf()))
                .andExpect(status().isNoContent());

        assertThat(noItemsList).isEmpty();
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
