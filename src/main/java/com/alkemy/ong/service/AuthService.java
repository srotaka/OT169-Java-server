package com.alkemy.ong.service;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<User> register (User user);

    ResponseEntity<AuthenticationResponse> login (String mail, String password) throws Exception;
}
