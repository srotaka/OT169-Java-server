package com.alkemy.ong.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
/**
 * @author Franco Lamberti
 */
public class AuthController {

	/*
	@PostMapping("/register")
	public ResponseEntity<User> register(
			String nombre,
			String apellido, 
			String email,
			String contrasena){
		User obj = userService.save(User);
		return new ResponseEntity<User>(obj, HttpStatus.OK);
	}
	*/
}
