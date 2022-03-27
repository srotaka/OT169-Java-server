package com.alkemy.ong.service;

import com.alkemy.ong.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    User findByEmail(String email) throws Exception;

    ResponseEntity<?> updatePartialInfo(String id, Map<Object, Object> fields);
}
