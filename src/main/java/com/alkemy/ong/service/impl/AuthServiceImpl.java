package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserCredentialsDto;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.AuthService;
import com.alkemy.ong.service.EmailService;
import com.alkemy.ong.utils.JwtUtils;
import com.alkemy.ong.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsCustomServiceImpl userDetailsCustomService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<AuthenticationResponse> register (User user) throws Exception {
        User findUser = userRepository.findByEmail(user.getEmail());

        if(findUser !=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        String oldPassword = user.getPassword();
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setRoleId(roleRepository.findById("2").get());
        userRepository.save(user); //guarda el usuario y automáticamente devuelve un objeto con mis datos json

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), oldPassword));
        } catch (
                BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = userDetailsCustomService.loadUserByUsername(user.getEmail());

        final String jwt = jwtUtils.generateToken(userDetails);
        emailService.sendWelcomeMail(user.getEmail(), user.getFirstName());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    public ResponseEntity<AuthenticationResponse> login(UserCredentialsDto credentials)  throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        } catch (
                BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = userDetailsCustomService.loadUserByUsername(credentials.getEmail());

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @Override
    public ResponseEntity<?> getAuthenticatedUserData(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String jwt = authorizationHeader.substring(7);
        String email = jwtUtils.extractEmail(jwt);
        User user = userRepository.findByEmail(email);

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Mapper.mapToUserDto(user));

    }

}
