package com.alkemy.ong.service;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserCredentialsDto;
import com.alkemy.ong.entity.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    ResponseEntity<AuthenticationResponse> register (User user) throws Exception;

    ResponseEntity<AuthenticationResponse> login (UserCredentialsDto credentials) throws Exception;

    ResponseEntity<?> getAuthenticatedUserData(HttpServletRequest httpServletRequest);
}
