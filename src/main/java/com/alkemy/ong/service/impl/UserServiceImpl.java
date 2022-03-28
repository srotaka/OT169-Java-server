package com.alkemy.ong.service.impl;

import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) throws Exception {

        if (email.isEmpty() || email == null){
            throw new Exception("Mail is empty");
        }

        User usuario = userRepository.findByEmail(email);
        if (usuario==null){
            throw new Exception("User not found");
        }

        return usuario;
    }
}
