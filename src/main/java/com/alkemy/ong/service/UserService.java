package com.alkemy.ong.service;

import com.alkemy.ong.entity.User;

public interface UserService {

    User findByEmail(String email) throws Exception;

    void delete(String id);
}
