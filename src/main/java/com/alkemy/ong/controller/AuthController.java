package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserCredentialsDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.service.AuthService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.alkemy.ong.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = "Authentication")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
    @ApiOperation(value = "Register a new User in the Data Base.",
                  consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully registered."),
            @ApiResponse(code = 400, message = "Bad Request, fields are missing or have incorrect type."),
            @ApiResponse(code = 500, message = "Email already exists.")
    })
	public ResponseEntity<AuthenticationResponse> register(@ApiParam("JSON with User data.")
                                                            @RequestBody User user) throws Exception {
		return authService.register(user);
	}

	@PostMapping("/login")
    @ApiOperation(value = "Authenticates User credentials and returns a JWT.",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully registered."),
            @ApiResponse(code = 400, message = "Invalid user credentials.")
    })
	public ResponseEntity<AuthenticationResponse> login(@ApiParam("JSON with User's email and associated password.")
                                                        @RequestBody @Valid UserCredentialsDto credentials)  throws Exception{
		return authService.login(credentials);
	}

	@GetMapping("/me")
    @ApiOperation(value = "Get the current User data.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User data successfully retrieved."),
            @ApiResponse(code = 401, message = "Valid JWT is missing in the request.")
    })
	public ResponseEntity<UserDto> getAuthenticateUserData(HttpServletRequest httpServletRequest){
		return (ResponseEntity<UserDto>) authService.getAuthenticatedUserData(httpServletRequest);
	}
}