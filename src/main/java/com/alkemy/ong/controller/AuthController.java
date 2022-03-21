package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.UserRepository;

@RestController
@RequestMapping("/auth")
/**
 * @author Franco Lamberti
 */
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired 
	private UserRepository userRepository; //This repository doesn't exists in this commit 
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){ //Not functional. User doesn't exists
		User obj = new User(); 
		obj.setFirstName(user.getFirstName());
		obj.setLastName(user.getLastName());
		obj.setEmail(user.getEmail());
		obj.setPassword( passwordEncoder.encode(user.getPassword()) );
		userRepository.save(obj);//This repository doesn't exists in this commit 
		return new ResponseEntity<User>(obj, HttpStatus.OK);
	}
	
}
