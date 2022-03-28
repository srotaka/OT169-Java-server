package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.entity.Category;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.RoleRepository;
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
	private RoleRepository roleRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){//recibe un json	
		System.out.println("Actual user: "+ user);
		System.out.println("User's role ID:" + user.getRole().getId());
		String encoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(encoded);
		user.setRole( roleRepository.getById( user.getRole().getId() ) );
		System.out.println("Actual user: "+ user);
		User obj = userRepository.save(user); //guarda el usuario y automáticamente devuelve un objeto con mis datos json
		
		
		return new ResponseEntity<User>(obj, HttpStatus.OK);//retorna una respuesta que contiene el user creado + el codigo 200
	}	
	
	@GetMapping("/categories")// OT169-40
	//@Secured("ROLE_ADMIN") // This method only permits the current role to enter this endpoint
	public List<Category> getNamesFromAll(){
		System.out.println("Get all");
		return categoryRepository.getNamesFromAll();
	}
	
}
