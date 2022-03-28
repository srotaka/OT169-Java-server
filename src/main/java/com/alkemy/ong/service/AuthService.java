package com.alkemy.ong.service;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    ResponseEntity<User> register (User user);

    ResponseEntity<AuthenticationResponse> login (String mail, String password) throws Exception;

    ResponseEntity<?> getAuthenticatedUserData(HttpServletRequest httpServletRequest);
}
