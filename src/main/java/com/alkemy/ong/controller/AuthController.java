package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
/**
 * @author Franco Lamberti
 */
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	@Autowired 
	private UserRepository userRepository; //This repository doesn't exists in this commit 
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){ //Not functional. User doesn't exists
		User obj = new User(); 
		obj.setNombre(user.getNombre());
		obj.setApellido(user.getApellido());
		obj.setEmail(user.getEmail());
		obj.setContrasena( passwordEncoder.encode(user.getContrasena()) );
		userRepository.save(obj);//This repository doesn't exists in this commit 
		return new ResponseEntity<User>(obj, HttpStatus.OK);
	}
	*/
}
