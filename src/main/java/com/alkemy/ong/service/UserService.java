package com.alkemy.ong.service;

import com.alkemy.ong.entity.User;
import com.amazonaws.services.pinpoint.model.ForbiddenException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    User findByEmail(String email) throws Exception;

    void delete(String id);

    ResponseEntity<?> updatePartialInfo(String id, Map<Object, Object> fields);

    ResponseEntity<?> getAllUser();

    void isUserAllowed(String id) throws ForbiddenException;
}
