package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserCredentialsDto;
import com.alkemy.ong.service.impl.UserDetailsCustomServiceImpl;
import com.alkemy.ong.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static com.alkemy.ong.controller.ActivitiesTest.asJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private UserCredentialsDto userCredentialsDto;

    private User user;

    private String jwt;

    @Autowired
    JwtUtils jwtUtil;

    @MockBean
    private UserDetailsCustomServiceImpl userDetailsCustomService;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    private final String commonUrl = "/auth";

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        userCredentialsDto = new UserCredentialsDto();
        userCredentialsDto.setEmail("eva@mail.com");
        userCredentialsDto.setPassword("1234");

        user = new User("eva@mail.com","1234", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        jwt = jwtUtil.generateToken(user);

    }


    @Test
    @DisplayName("Login user with its credentianls and should return a 200 an a authentication response with the jwt")
    void logiUser__ShouldLoginUserAndReturnOkAndJwt() throws Exception{

        usernamePasswordAuthenticationToken.setDetails(userCredentialsDto);

        when(userDetailsCustomService.loadUserByUsername(userCredentialsDto.getEmail())).thenReturn(user);
        when(usernamePasswordAuthenticationToken.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(any());

        mockMvc.perform(post(commonUrl +"/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(userCredentialsDto)))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.jwt").value(jwt)));
    }


}
