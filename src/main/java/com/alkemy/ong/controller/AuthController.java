package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.alkemy.ong.entity.User;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) throws Exception {
		return authService.register(user);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestParam String mail,@RequestParam String password)  throws Exception{
		return authService.login(mail,password);
	}

	@GetMapping("/me")
	public ResponseEntity<?> getAuthenticateUserData(HttpServletRequest httpServletRequest){
		return authService.getAuthenticatedUserData(httpServletRequest);
	}
}