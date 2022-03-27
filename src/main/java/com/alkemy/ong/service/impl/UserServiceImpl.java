package com.alkemy.ong.service.impl;

import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
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

    @Override
    public ResponseEntity<?> updatePartialInfo(String id, Map<Object, Object> fields) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();

        }

        try{
        fields.forEach((key,value) -> {
           Field field = ReflectionUtils.findField(User.class,(String)key);
           field.setAccessible(true);
           ReflectionUtils.setField(field,optionalUser.get(),value);
        });
        User updatedUser = userRepository.save(optionalUser.get());

        return new ResponseEntity<User>(updatedUser,HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
