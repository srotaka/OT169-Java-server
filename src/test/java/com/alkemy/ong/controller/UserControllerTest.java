package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.impl.UserServiceImpl;
import com.alkemy.ong.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@TestPropertySource(locations = "")
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    @MockBean
    UserServiceImpl userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;
    @Autowired
    UserController userController;
    @Autowired
    JwtUtils jwtUtils;

    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    private final String URL = "/users";

    Role admin = new Role();
    Role user = new Role();

    User userEntity = new User();
    User userEntity2 = new User();
    UserDto userDto = new UserDto();

    List<User> userEntityList = new ArrayList<>();
    List<UserDto> userDtoList = new ArrayList<>();
    List<String> listToDisplayUserEmail = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        admin.setId("1");
        admin.setName("ADMIN");
        admin.setDescription("Admistrador General");

        user.setId("2");
        user.setName("USER");
        user.setDescription("Usuario del Sistema");

        userEntity.setId("101");
        userEntity.setFirstName("Harry");
        userEntity.setLastName("Potter");
        userEntity.setEmail("harry@potter.com");
        userEntity.setPassword("1234");
        userEntity.setPhoto("harry-potter.jpg");
        userEntity.setRoleId(admin);

        userEntity2.setId("102");
        userEntity2.setFirstName("Hermione");
        userEntity2.setLastName("Granger");
        userEntity2.setEmail("hermione@granger.com");
        userEntity2.setPassword("1234");
        userEntity2.setPhoto("hermione.jpg");
        userEntity2.setRoleId(user);

        userDto.setFirstName("Harry");
        userDto.setLastName("Potter");
        userDto.setEmail("harry@potter.com");
        userDto.setPhoto("harry-potter.jpg");

        userEntityList.add(userEntity);
        userDtoList.add(userDto);

        listToDisplayUserEmail.add("harry@potter.com");
        listToDisplayUserEmail.add("hermione@granger.com");

    }

    /* ======================================
        TESTS FOR GETTING ALL USERS
    =========================================*/

    @DisplayName("Get All Users: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    @Test
    void getAllUsers__Success() throws Exception {

        when(userRepository.findAll()).thenReturn(userEntityList);
        mockMvc.perform(get(URL)
                .contentType(APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk());

        assertThat(listToDisplayUserEmail).hasSize(2);
        assertThat(listToDisplayUserEmail).contains("harry@potter.com", "hermione@granger.com");

    }

    @DisplayName("Fail Getting All Users because user is not authenticated (Error 401 Unauthorized)")
    @Test
    void getAllUsers__FailBecauseUserIsNotAuthenticated() throws Exception {

        when(userRepository.findAll()).thenReturn(userEntityList);
        mockMvc.perform(get(URL)
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("Fail Getting All Users due to incorrect role (Error 403 Forbidden)")
    @WithMockUser(roles = "USER")
    @Test
    void getAllUsers__FailBecauseUserIsNotAdmin() throws Exception {

        when(userRepository.findAll()).thenReturn(userEntityList);
        mockMvc.perform(get(URL)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(listToDisplayUserEmail))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @DisplayName("Fail getting All Users because List is empty (Error 204 No Content)")
    @WithMockUser(roles = "ADMIN")
    @Test
    void getAllUsers__FailBecauseListIsEmpty() throws Exception {
        List<User> noItemsList = new ArrayList<>();
        when(userController.getAllUsers()).thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));

        mockMvc.perform(get(URL)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(noItemsList))
                        .with(csrf()))
                .andExpect(status().isNoContent());

        assertThat(noItemsList).isEmpty();
    }

    /* ======================================
     TESTS FOR UPDATING A USER
    =========================================*/
    @Test
    @DisplayName("Update User: Success Code (200 OK) when being an administrator or user modifying him/herself")
    void updateUser__Success() throws Exception {
        userEntity.setEmail("harry-potter@mail.com");

        Map<Object, Object> fields = new HashMap<>();
        fields.put("email","harry-potter@mail.com" );

        userService.isUserAllowed("101");
        userService.updatePartialInfo("101", fields);

        mockMvc.perform(patch(URL+"/101")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userEntity))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());

        assertThat(userEntity.getId()).isEqualTo("101");
        assertThat(userEntity.getFirstName()).isEqualTo("Harry");
        assertThat(userEntity.getEmail()).isEqualTo("harry-potter@mail.com");

    }
    @Test
    @DisplayName("Fail Updating User is not authenticated (Error 401 Unauthorized)")
    void updateUser__FailBecauseUserIsNotAuthenticated() throws Exception {

        mockMvc.perform(patch(URL+"/101")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }
    @Test
    @DisplayName("Fail Updating User when user is trying to modify another user info (Error 403 Forbidden)")
    @WithMockUser(roles = "USER")
    void updateUser__FailBecauseUserTriesToUpdateAnotherUserInfo() throws Exception {

        Map<Object, Object> fields = new HashMap<>();
        fields.put("email","hermione-potter@mail.com" );

        when(userService.updatePartialInfo("101", fields)).thenThrow(new ResponseStatusException(HttpStatus.FORBIDDEN));

        mockMvc.perform(patch(URL+"/101")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(fields))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Fail Updating User due to non-existent ID (Error 404 Not Found)")
    @WithMockUser(roles = "ADMIN")
    void updateUser__FailBecauseIdNotFound() throws Exception {

        userEntity.setEmail("harry-potter@mail.com");
        Map<Object, Object> fields = new HashMap<>();
        fields.put("email","harry-potter@mail.com" );

        when(userService.updatePartialInfo("nonExistingUserId", fields)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        mockMvc.perform(patch(URL+"nonExistingUserId")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userEntity)))
                .andExpect(status().isNotFound());
    }

    /* ======================================
     TESTS FOR DELETING A USER
    =========================================*/

    @Test
    @DisplayName("Delete User: Success (Code 200 OK)")
    @WithMockUser(roles = "ADMIN")
    void deleteUser__Success() throws Exception {

        when(userController.delete("101")).thenThrow(new ResponseStatusException(HttpStatus.OK));
        mockMvc.perform(delete(URL+"/101")
                        .contentType(APPLICATION_JSON)
                        //.with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Fail Deleting User is not authenticated (Error 401 Unauthorized)")
    void deleteUser__FailBecauseUserIsNotAuthenticated() throws Exception {
        mockMvc.perform(delete(URL+"/101")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Fail Deleting User when user is trying to delete user (Error 403 Forbidden)")
    @WithMockUser(roles = "USER")
    void deleteUser__FailBecauseUserTriesToDeleteAnotherUser() throws Exception {

        when(userRepository.findById("101")).thenReturn(Optional.of(userEntity));
        doThrow(new ResponseStatusException(HttpStatus.FORBIDDEN)).when(userService).delete("101");

        mockMvc.perform(delete(URL+"/101")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userEntity))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Fail deleting User due to non-existent ID (Error 404 Not Found)")
    @WithMockUser(roles = "ADMIN")
    void deleteUser__FailBecauseIdNotFound() throws Exception {

        when(userController.delete("nonExistingUserId")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        mockMvc.perform(delete(URL+"nonExistingUserId")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userEntity)))
                .andExpect(status().isNotFound());

    }
}