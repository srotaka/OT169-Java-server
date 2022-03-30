package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.entity.User;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired

	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	/*
	* Modification of registration method
	* @author Nico Pistone
	* */
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) throws Exception {
		userRepository.save(user);

		UserDetails userDetails;


		try{
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

			userDetails=(UserDetails) auth.getPrincipal();
		}catch (BadCredentialsException e){
			throw new Exception("Incorrect username or password", e);
		}
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

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