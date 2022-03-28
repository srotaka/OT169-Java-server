package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.AuthService;
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
import java.util.Optional;

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

    public ResponseEntity<User> register (User user) {

        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setRoleId(roleRepository.findById(user.getRoleId().getId()).get());
        User obj = userRepository.save(user); //guarda el usuario y automáticamente devuelve un objeto con mis datos json

        return new ResponseEntity<User>(obj, HttpStatus.OK);
    }

    public ResponseEntity<AuthenticationResponse> login(String mail, String password)  throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(mail, password));
        } catch (
                BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = userDetailsCustomService.loadUserByUsername(mail);

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
